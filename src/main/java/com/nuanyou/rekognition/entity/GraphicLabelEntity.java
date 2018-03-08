package com.nuanyou.rekognition.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by mylon.sun on 2017/12/8.
 */
@Entity
@Table(name = "ai_graphic_label")
public class GraphicLabelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "graphicid")
    private Long graphicId;

    @Column(name = "confidence")
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

    public BigDecimal getConfidence() {
        return confidence;
    }

    public void setConfidence(BigDecimal confidence) {
        this.confidence = confidence;
    }
}
