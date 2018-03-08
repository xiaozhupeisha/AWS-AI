package com.nuanyou.rekognition.model.vo;

import java.math.BigDecimal;

/**
 * Created by mylon.sun on 2017/12/10.
 */
public class GraphicLabelVo {

    private Long id;

    private String name;

    private Long graphicId;

    private String iconClass;

    private BigDecimal confidence;

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

    public Long getGraphicId() {
        return graphicId;
    }

    public void setGraphicId(Long graphicId) {
        this.graphicId = graphicId;
    }

    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }

    public BigDecimal getConfidence() {
        return confidence;
    }

    public void setConfidence(BigDecimal confidence) {
        this.confidence = confidence;
    }
}
