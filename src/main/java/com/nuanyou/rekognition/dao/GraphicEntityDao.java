package com.nuanyou.rekognition.dao;

import com.nuanyou.rekognition.entity.GraphicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by mylon.sun on 2017/12/8.
 */
public interface GraphicEntityDao extends JpaRepository<GraphicEntity, Long>, JpaSpecificationExecutor {

    GraphicEntity findByName(String imageName);

    @Query(value = "SELECT * FROM ai_graphic ORDER BY confidence DESC LIMIT 0,30", nativeQuery = true)
    List<GraphicEntity> getTopImages();

    @Query(value = "SELECT * FROM ai_graphic WHERE id IN ?1 ORDER BY confidence DESC", nativeQuery = true)
    List<GraphicEntity> getTopImagesIn(List<Long> ids);

}
