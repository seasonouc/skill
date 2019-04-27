package com.hanson.test.skill.config;

import com.hanson.test.common.utils.ProtostuffUtils;
import com.hanson.test.skill.ProcessorApplication;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProcessorApplication.class)
public class ConfigTest {

    @Autowired
    private DefaultMQPushConsumer mqConsumer;

    @Test
    public void testConsumer(){
        try {
            mqConsumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                                ConsumeConcurrentlyContext context) {
                    for(MessageExt messageExt:msgs){
                        byte bytes[] = messageExt.getBody();
                        String body = ProtostuffUtils.deserialize(bytes,String.class);
                        System.out.println(body);
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            mqConsumer.start();
            Thread.sleep(10000);
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}