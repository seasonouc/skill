package com.hanson.test.skill.config;

import com.hanson.test.common.utils.ProtostuffUtils;
import com.hanson.test.skill.AcceptorApplication;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = AcceptorApplication.class)
public class ConfigTest {

    @Autowired
    private MQProducer mqProducer;


    @Value("${mq.rocketMq.topic}")
    private String topic;

    @Test
    public void testProducer() throws InterruptedException {
        for(int i=0;i<100;i++){
            try {

                byte[] bytes = ProtostuffUtils.serialize("Hello RocketMQ " + i);
                Message msg = new Message(topic /* Topic */,
                        "TagA" /* Tag */,
                        bytes /* Message body */
                );

                /*
                 * Call send message to deliver message to one of brokers.
                 */
                SendResult sendResult = mqProducer.send(msg);

                System.out.printf("%s%n", sendResult);
            }catch(Exception e){
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }
        mqProducer.shutdown();
    }
}