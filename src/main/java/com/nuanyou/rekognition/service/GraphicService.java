package com.nuanyou.rekognition.service;

import com.amazonaws.services.rekognition.model.FaceDetail;
import com.amazonaws.services.rekognition.model.TextDetection;
import com.nuanyou.rekognition.model.vo.GraphicVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by mylon.sun on 2017/12/8.
 */
public interface GraphicService {

    void _label(MultipartFile file) throws IOException;

    List<GraphicVo> getTopImages();

    List<GraphicVo> _detect(String key);

    String _face(MultipartFile faceFile) throws IOException;

    String _text(MultipartFile textFile) throws IOException;

}
