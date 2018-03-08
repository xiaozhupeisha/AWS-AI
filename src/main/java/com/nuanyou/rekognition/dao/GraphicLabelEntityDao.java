package com.nuanyou.rekognition.dao;

import com.nuanyou.rekognition.entity.GraphicLabelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by mylon.sun on 2017/12/8.
 */
public interface GraphicLabelEntityDao extends JpaRepository<GraphicLabelEntity, Long>, JpaSpecificationExecutor {

    @Query(value = "SELECT * FROM ai_graphic_label WHERE name LIKE %:name%", nativeQuery = true)
    List<GraphicLabelEntity> findByNameLike(@Param("name") String name);

    @Modifying
    @Transactional
    @Query(value = "update GraphicLabelEntity t set t.name=?1 where t.id=?2")
    void updateName(String name, Long id);

    List<GraphicLabelEntity> findByGraphicIdIn(List<Long> ids);

}
