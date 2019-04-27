package com.hanson.test.skill;


import com.hanson.test.common.request.SKillBuyRequestVO;
import com.hanson.test.common.utils.ProtostuffUtils;
import com.hanson.test.skill.service.ProcessService;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.util.List;

@SpringBootApplication
@MapperScan("com.hanson.test.skill.dao")
public class ProcessorApplication implements CommandLineRunner {
    @Autowired
    private DefaultMQPushConsumer mqConsumer;

    @Resource
    private ProcessService processService;

    public static void main(String args[]) {
        SpringApplication.run(ProcessorApplication.class);
    }

    @Override
    public void run(String... args) throws MQClientException {
        mqConsumer.registerMessageListener(new MessageListenerConcurrently(){
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                for(MessageExt msg:msgs){
                    byte[] data = msg.getBody();
                    SKillBuyRequestVO requestVO = ProtostuffUtils.deserialize(data,SKillBuyRequestVO.class);
                    processService.process(requestVO);
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        mqConsumer.start();
    }

}
