package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.config.RabbitMQConfig;
import org.example.controller.params.MessageParam;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author huangzhenyu
 * @date 2023/5/7
 */
@Slf4j
@Service
public class RabbitMQService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendDelayMessage(MessageParam message, int delayTime) throws JsonProcessingException {
        log.info("message= " + message.getMessage() + ", delayTime=" + delayTime);
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setDelay(delayTime * 1000);
        ObjectMapper objectMapper = new ObjectMapper();
        Message msg = new Message(objectMapper.writeValueAsString(message).getBytes(), messageProperties);

        rabbitTemplate.send(RabbitMQConfig.DELAY_EXCHANGE_NAME, RabbitMQConfig.DELAY_ROUTING_KEY, msg);
    }
}
