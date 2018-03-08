package com.nuanyou.rekognition.config;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.nuanyou.rekognition.util.ContentTypeUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component("s3")
public class AwsS3Client extends FileClient {

    private AmazonS3 client;

    private String bucketName;

    private String region;

    public AwsS3Client(@Value("${s3.bucketName}") String bucketName,
                       @Value("${s3.domain}") String domain,
                       @Value("${s3.region}") String region) {
        this.region = region;
        this.bucketName = bucketName;
        this.client = new AmazonS3Client();
        client.setRegion(Region.getRegion(Regions.fromName(this.region)));
    }

    /**
     * 上传文件
     *
     * @param key
     * @param is
     * @return
     * @throws IOException
     */
    public String uploadFile(String key, InputStream is) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(is.available());
        metadata.setContentType(ContentTypeUtils.getContentType(key));
        client.putObject(bucketName, key, is, metadata);

        AccessControlList acl = client.getObjectAcl(bucketName, key);
        acl.revokeAllPermissions(GroupGrantee.AllUsers);
        acl.grantPermission(GroupGrantee.AllUsers, Permission.Read);
        SetObjectAclRequest request = new SetObjectAclRequest(bucketName, key, acl);
        client.setObjectAcl(request);
        return key;
    }

    /**
     * 设置标签
     *
     * @param key
     * @param newTags
     * @return
     * @throws Exception
     */
    public void setTaggings(String key, List<Tag> newTags) {
        client.setObjectTagging(new SetObjectTaggingRequest(bucketName, key, new ObjectTagging(newTags)));
    }

    /**
     * 获取文件
     *
     * @param key
     * @return
     * @throws IOException
     */
    public S3Object acquire(String key) throws IOException {
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, key);
        return client.getObject(getObjectRequest);
    }

}