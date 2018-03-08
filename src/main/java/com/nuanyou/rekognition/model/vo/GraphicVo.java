package com.nuanyou.rekognition.model.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by mylon.sun on 2017/12/10.
 */
public class GraphicVo {

    private Long id;

    private String name;

    private String bucket;

    private String redirectLocation;

    private BigDecimal confidence;

    private Date createTime;

    private List<GraphicLabelVo> labels;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getRedirectLocation() {
        return redirectLocation;
    }

    public void setRedirectLocation(String redirectLocation) {
        this.redirectLocation = redirectLocation;
    }

    public BigDecimal getConfidence() {
        return confidence;
    }

    public void setConfidence(BigDecimal confidence) {
        this.confidence = confidence;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<GraphicLabelVo> getLabels() {
        return labels;
    }

    public void setLabels(List<GraphicLabelVo> labels) {
        this.labels = labels;
    }
}
