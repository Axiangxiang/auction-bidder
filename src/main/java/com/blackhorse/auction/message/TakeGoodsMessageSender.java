package com.blackhorse.auction.message;

import com.blackhorse.auction.model.TakeGoodsMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TakeGoodsMessageSender {

    private final ObjectMapper mapper;
    private final RabbitTemplate rabbitTemplate;

    public boolean send(TakeGoodsMessage takeGoodsMessage) {
        try {
            String message = mapper.writeValueAsString(takeGoodsMessage);
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUNTING_KEY, message);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
