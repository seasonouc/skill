package com.hanson.test.skill.controller;


import com.hanson.test.common.request.SKillBuyRequestVO;
import com.hanson.test.common.request.SKillStockQueryVO;
import com.hanson.test.common.response.SKillBuyResponseVO;
import com.hanson.test.common.response.SKillStockResponseVO;
import com.hanson.test.common.utils.ProtostuffUtils;
import com.hanson.test.skill.service.CacheService;
import lombok.extern.java.Log;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/skill")
@Log
public class AcceptController {

    @Autowired
    private MQProducer mqProducer;

    @Value("${mq.rocketMq.topic}")
    private String topic;

    @Resource
    private CacheService cacheService;


    @RequestMapping(value = "/buy",method = RequestMethod.POST)
    public SKillBuyResponseVO skillBuy(@RequestBody SKillBuyRequestVO requestVO)  {
        SKillBuyResponseVO responseVO = new SKillBuyResponseVO();
        if(cacheService.getStock(requestVO.getKillId()) <= 0){
            responseVO.setMessage("动作慢了一步");
        }else{
            try {
                byte[] bytes = ProtostuffUtils.serialize(requestVO);
                Message message = new Message(topic, requestVO.getKillId(), bytes);
                mqProducer.send(message);
            }catch(Exception e){
                responseVO.setMessage("System.error");
                responseVO.setResponseCode(300);
            }
        }
        return responseVO;
    }

    @RequestMapping()
    public SKillStockResponseVO getStock(@RequestBody SKillStockQueryVO queryVO){
        SKillStockResponseVO responseVO = new SKillStockResponseVO();
        return responseVO;
    }

}
