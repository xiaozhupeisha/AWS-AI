package image;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.*;

import java.util.List;

/**
 * Created by mylon.sun on 2017/12/8.
 */
public class ImageText {

    public static void main(String[] args) {
        AmazonRekognition amazonRekognition = AmazonRekognitionClientBuilder.standard()
                .withRegion(Regions.US_WEST_2)
                .build();

        DetectTextRequest detectTextRequest = new DetectTextRequest()
                .withImage(new Image()
                        .withS3Object(new com.amazonaws.services.rekognition.model.S3Object().withBucket("cf-templates-m66jhmg23fv9-us-west-2")
                                .withName("coffee_monday_resized.jpg")));

        DetectTextResult detectTextResult = amazonRekognition.detectText(detectTextRequest);

        List<TextDetection> textDetections = detectTextResult.getTextDetections();
        for (TextDetection textDetection : textDetections) {
            System.out.println(textDetection.toString());
        }

    }

}
