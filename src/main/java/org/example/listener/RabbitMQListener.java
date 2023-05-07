package org.example.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.example.config.RabbitMQConfig;
import org.example.controller.params.MessageParam;
import org.example.utils.EmailUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;

@Slf4j
@Component
public class RabbitMQListener {
    @Resource
    private EmailUtils emailUtils;

    @RabbitListener(queues = RabbitMQConfig.DELAY_QUEUE_NAME, ackMode = "MANUAL")
    public void receiveMsg(Message message, Channel channel) throws IOException {

        String msg = new String(message.getBody());

        ObjectMapper objectMapper = new ObjectMapper();
        MessageParam messageParam = objectMapper.readValue(msg, MessageParam.class);

        log.info("当前时间为{}，收到延迟消息为{}", new Date(), messageParam.getMessage());
        emailUtils.sendMessage(messageParam.getTo(), "备忘录提醒", messageParam.getMessage());

        // 手动确认消息已被消费
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

    }

}
   
