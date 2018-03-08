package image;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.DetectLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectLabelsResult;
import com.amazonaws.services.rekognition.model.Image;

/**
 * Created by mylon.sun on 2017/11/28.
 */
public class ImageLabel {

    public static void main(String[] args) {
        AmazonRekognition amazonRekognition = AmazonRekognitionClientBuilder.standard()
                .withRegion(Regions.US_WEST_2)
                .build();

        DetectLabelsRequest detectLabelsRequest = new DetectLabelsRequest()
                .withMinConfidence(10F)
                .withMaxLabels(50)
                .withImage(new Image()
                        .withS3Object(new com.amazonaws.services.rekognition.model.S3Object().withBucket("cf-templates-m66jhmg23fv9-us-west-2")
                                .withName("city_resized.jpg")));

        DetectLabelsResult detectLabelsResult = amazonRekognition.detectLabels(detectLabelsRequest);
        System.out.println(detectLabelsResult.getOrientationCorrection());
        System.out.println(detectLabelsResult.getLabels());
        for (com.amazonaws.services.rekognition.model.Label label : detectLabelsResult.getLabels()) {
            System.out.println("name：" + label.getName() + "，confidence：" + label.getConfidence());
        }
    }

}
