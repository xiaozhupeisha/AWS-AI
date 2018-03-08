package sns;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.SetSubscriptionAttributesRequest;

/**
 * Created by mylon.sun on 2017/12/15.
 */
public class SetAtt {

    public static void main(String[] args) {
        //创建aws客户端
        AmazonSNS snsClient = AmazonSNSClientBuilder.standard().withRegion("ap-northeast-2").build();
        //创建订阅
        String arn = "arn:aws:sns:ap-northeast-2:346868767728:test_mylon:d3064644-1065-4be8-a37f-d2974038caba";
        String filterPolicyString1 = "{\"screen_condition\":[\"121\", \"2\"]}";
        SetSubscriptionAttributesRequest request1 =
                new SetSubscriptionAttributesRequest(arn, "FilterPolicy", filterPolicyString1);
        snsClient.setSubscriptionAttributes(request1);

    }

}
