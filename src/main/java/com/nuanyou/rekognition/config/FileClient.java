package com.nuanyou.rekognition.config;

import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.Tag;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class FileClient {

    abstract String uploadFile(String key, InputStream is) throws IOException;

    public String uploadFile(InputStream is, String suffix) throws IOException {
        return uploadFile(buildPath(suffix), is);
    }

    public void setTaggings(String key, List<Tag> newTags) {
        this.setTaggings(key, newTags);
    }

    public S3Object acquire(String key) throws IOException {
        return this.acquire(key);
    }

    public static String buildPath(String suffix) {
        StringBuilder filePath = new StringBuilder();
        filePath.append(System.currentTimeMillis());
        filePath.append(String.valueOf(Math.abs(ThreadLocalRandom.current().nextLong())).substring(0, 7));

        if (!suffix.startsWith("."))
            filePath.append(".");
        filePath.append(suffix);
        return filePath.toString();
    }

}