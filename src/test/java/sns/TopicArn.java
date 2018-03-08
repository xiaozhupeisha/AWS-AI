package sns;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by mylon.sun on 2017/12/13.
 */
public class TopicArn {

    public static void main(String[] args) {

        AmazonSNS client = AmazonSNSClientBuilder.standard()
                .withRegion("ap-southeast-1").build();

        // arn:aws:sns:ap-northeast-2:346868767728:test_mylon
        // CreateTopicResult test_mylon = client.createTopic("test_mylon");

        /*SubscribeRequest subscribeRequest = new SubscribeRequest()
                .withTopicArn("arn:aws:sns:ap-northeast-2:346868767728:test_mylon")
                .withProtocol("email")
                .withEndpoint("mylon.sun@91nuanyou.com");

        SubscribeResult subscribe = client.subscribe(subscribeRequest);
        System.out.println(subscribe.getSubscriptionArn());*/

        List<String> attributeValues = Lists.newArrayList();
        attributeValues.add("A");
        attributeValues.add("10");

        String valuesString, delimiter = ", ", prefix = "[", suffix = "]";
        if (attributeValues.get(0).getClass() == String.class) {
            delimiter = "\", \"";
            prefix = "[\"";
            suffix = "\"]";
        }
        valuesString = attributeValues
                .stream()
                .map(value -> value.toString())
                .collect(Collectors.joining(delimiter, prefix, suffix));

        Map<String, MessageAttributeValue> map = Maps.newHashMap();
        MessageAttributeValue userType = new MessageAttributeValue();
        userType.setDataType("String.Array");
        userType.setStringValue(valuesString);
        map.put("screen_condition", userType);

        PublishRequest publishRequest = new PublishRequest()
                .withMessage("孙日天")
                .withSubject("孙日天")
                .withMessageAttributes(map)
                .withTopicArn("arn:aws:sns:ap-southeast-1:346868767728:testzhuti");

        PublishResult publish = client.publish(publishRequest);
        System.out.println(publish.getMessageId());

        /*SetSubscriptionAttributesRequest request2 =
                new SetSubscriptionAttributesRequest()
                .withAttributeName("DeliveryPolicy")
                .withAttributeValue("{\"userSub2\": \"d0dfbf74-28b6-4122-b8d4-6babdc82f1ba\"}")
                .withSubscriptionArn("arn:aws:sns:ap-southeast-1:346868767728:testzhuti:a7073cc7-a3d5-4d64-a8ec-db6f9fc3adcc");
        client.setSubscriptionAttributes(request2);*/

    }

}
