package com.nuanyou.rekognition.service;

import com.alibaba.fastjson.JSONObject;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.*;
import com.amazonaws.services.s3.model.Tag;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.nuanyou.rekognition.config.FileClient;
import com.nuanyou.rekognition.dao.GraphicEntityDao;
import com.nuanyou.rekognition.dao.GraphicLabelEntityDao;
import com.nuanyou.rekognition.entity.GraphicEntity;
import com.nuanyou.rekognition.entity.GraphicLabelEntity;
import com.nuanyou.rekognition.model.LabelClassEnum;
import com.nuanyou.rekognition.model.vo.GraphicLabelVo;
import com.nuanyou.rekognition.model.vo.GraphicVo;
import com.nuanyou.rekognition.model.vo.TransVo;
import com.nuanyou.rekognition.util.BeanUtils;
import com.nuanyou.rekognition.util.TransApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by mylon.sun on 2017/12/8.
 */
@Service
public class GraphicServiceImpl implements GraphicService {

    @Autowired
    private GraphicEntityDao graphicEntityDao;

    @Autowired
    private GraphicLabelEntityDao graphicLabelEntityDao;

    @Autowired
    @Qualifier("s3")
    private FileClient fileClient;

    @Value("${s3.bucketName}")
    private String bucketName;

    @Value("${s3.domain}")
    private String domain;

    private static final String APP_ID = "20171211000103885";

    private static final String SECURITY_KEY = "5nBXXo457IRPiQ_B4cSi";

    @Override
    public List<GraphicVo> getTopImages() {
        List<GraphicEntity> topImages = graphicEntityDao.getTopImages();

        List<GraphicVo> graphicVos = this.getGraphicVos(topImages);

        return graphicVos;
    }

    @Override
    public List<GraphicVo> _detect(String key) {
        List<GraphicLabelEntity> labels = graphicLabelEntityDao.findByNameLike(key);

        Set<Long> graphicIds = Sets.newHashSet();
        for (GraphicLabelEntity label : labels) {
            graphicIds.add(label.getGraphicId());
        }

        if (CollectionUtils.isEmpty(graphicIds)) {
            return Lists.newArrayList();
        }

        List<GraphicEntity> topImages = graphicEntityDao.getTopImagesIn(Lists.newArrayList(graphicIds));

        List<GraphicVo> graphicVos = this.getGraphicVos(topImages);

        return graphicVos;
    }

    @Override
    public void _label(MultipartFile multipartFile) throws IOException {
        String key = fileClient.uploadFile(multipartFile.getInputStream(), "jpg");
        AmazonRekognition amazonRekognition = AmazonRekognitionClientBuilder.standard()
                .withRegion(Regions.US_WEST_2)
                .build();

        DetectLabelsRequest detectLabelsRequest = new DetectLabelsRequest()
                .withMinConfidence(10F)
                .withMaxLabels(6)
                .withImage(new Image()
                        .withS3Object(new com.amazonaws.services.rekognition.model.S3Object().withBucket(bucketName).withName(key)));

        DetectLabelsResult detectLabelsResult = amazonRekognition.detectLabels(detectLabelsRequest);

        Long graphicId = null;
        Float confidence = 0F;
        Integer labelLength = 0;
        for (com.amazonaws.services.rekognition.model.Label label : detectLabelsResult.getLabels()) {
            labelLength++;
            confidence = confidence + label.getConfidence();
        }

        String imageName = multipartFile.getOriginalFilename();
        if (graphicEntityDao.findByName(imageName) == null) {
            GraphicEntity graphicEntity = new GraphicEntity();
            graphicEntity.setBucket(bucketName);
            graphicEntity.setCreateTime(new Date());
            graphicEntity.setDelFlag(false);
            graphicEntity.setName(imageName);
            graphicEntity.setRedirectLocation(domain + "/" + bucketName + "/" + key);
            graphicEntity.setConfidence(new BigDecimal(confidence / labelLength));

            GraphicEntity result = graphicEntityDao.save(graphicEntity);
            graphicId = result.getId();
        }


        List<GraphicLabelEntity> labelEntities = Lists.newArrayList();
        for (com.amazonaws.services.rekognition.model.Label label : detectLabelsResult.getLabels()) {
            GraphicLabelEntity graphicLabelEntity = new GraphicLabelEntity();
            graphicLabelEntity.setGraphicId(graphicId);
            TransApi api = new TransApi(APP_ID, SECURITY_KEY);
            String transResult = api.getTransResult(label.getName(), "auto", "zh");
            TransVo transVo = JSONObject.parseObject(transResult, TransVo.class);
            if (transVo == null || CollectionUtils.isEmpty(transVo.getTrans_result())) {
                graphicLabelEntity.setName(label.getName());
            } else {
                graphicLabelEntity.setName(transVo.getTrans_result().get(0).getDst());
            }
            graphicLabelEntity.setConfidence(new BigDecimal(label.getConfidence()));
            labelEntities.add(graphicLabelEntity);
        }
        graphicLabelEntityDao.save(labelEntities);

        List<Tag> tages = Lists.newArrayList();
        for (Label label : detectLabelsResult.getLabels()) {
            tages.add(new Tag(label.getName(), label.getConfidence().toString()));
        }

        fileClient.setTaggings(key, tages);
    }

    @Override
    public String _face(MultipartFile faceFile) throws IOException {
        String key = fileClient.uploadFile(faceFile.getInputStream(), "jpg");

        AmazonRekognition amazonRekognition = AmazonRekognitionClientBuilder.standard()
                .withRegion(Regions.US_WEST_2)
                .build();

        DetectFacesRequest detectFacesRequest = new DetectFacesRequest()
                .withImage(new Image()
                        .withS3Object(new com.amazonaws.services.rekognition.model.S3Object().withBucket(bucketName).withName(key)));

        DetectFacesResult detectFacesResult = amazonRekognition.detectFaces(detectFacesRequest);
        for (FaceDetail faceDetail : detectFacesResult.getFaceDetails()) {
            return JSONObject.toJSON(faceDetail).toString();
        }

        return null;
    }

    @Override
    public String _text(MultipartFile textFile) throws IOException {
        String key = fileClient.uploadFile(textFile.getInputStream(), "jpg");
        AmazonRekognition amazonRekognition = AmazonRekognitionClientBuilder.standard()
                .withRegion(Regions.US_WEST_2)
                .build();

        DetectTextRequest detectTextRequest = new DetectTextRequest()
                .withImage(new Image()
                        .withS3Object(new com.amazonaws.services.rekognition.model.S3Object().withBucket(bucketName).withName(key)));

        DetectTextResult detectTextResult = amazonRekognition.detectText(detectTextRequest);

        if (CollectionUtils.isEmpty(detectTextResult.getTextDetections())) {
            return null;
        }

        return JSONObject.toJSON(detectTextResult.getTextDetections().get(0)).toString();
    }

    /**
     * 构造
     *
     * @param topImages
     * @return
     */
    private List<GraphicVo> getGraphicVos(List<GraphicEntity> topImages) {
        List<GraphicVo> graphicVos = Lists.newArrayList();
        List<Long> ids = Lists.newArrayList();

        for (GraphicEntity topImage : topImages) {
            GraphicVo graphicVo = new GraphicVo();
            graphicVos.add(BeanUtils.copyBeanNotNull(topImage, graphicVo));
            ids.add(topImage.getId());
        }

        Map<Long, List<GraphicLabelVo>> labelVoMap = Maps.newHashMap();
        List<GraphicLabelEntity> labels = graphicLabelEntityDao.findByGraphicIdIn(ids);
        for (GraphicLabelEntity label : labels) {
            GraphicLabelVo graphicLabelVo = new GraphicLabelVo();
            GraphicLabelVo vo = BeanUtils.copyBeanNotNull(label, graphicLabelVo);
            if (labelVoMap.containsKey(vo.getGraphicId())) {
                vo.setIconClass(LabelClassEnum.toEnum(labelVoMap.get(vo.getGraphicId()).size() + 1).getValue());
                labelVoMap.get(vo.getGraphicId()).add(vo);
                continue;
            }
            vo.setIconClass(LabelClassEnum.level1.getValue());
            labelVoMap.put(vo.getGraphicId(), Lists.newArrayList(vo));
        }

        for (GraphicVo vo : graphicVos) {
            if (!labelVoMap.containsKey(vo.getId())) {
                continue;
            }
            List<GraphicLabelVo> graphicLabelVos = labelVoMap.get(vo.getId());
            vo.setLabels(graphicLabelVos);
        }
        return graphicVos;
    }


}
