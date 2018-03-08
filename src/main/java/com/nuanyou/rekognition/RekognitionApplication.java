package com.nuanyou.rekognition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by Byron on 2017/8/29.
 */
@EnableCaching
@EnableScheduling
@SpringBootApplication
public class RekognitionApplication {

    private static final Logger _LOGGER = LoggerFactory.getLogger(RekognitionApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RekognitionApplication.class);
        _LOGGER.info("Nuanyou Rekognition Application：start success! 启动成功！");
    }
}
