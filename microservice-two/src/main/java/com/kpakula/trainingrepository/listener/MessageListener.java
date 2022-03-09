package com.kpakula.trainingrepository.listener;

import com.kpakula.trainingrepository.dto.User;
import com.kpakula.trainingrepository.config.MessageConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private final Logger logger = LoggerFactory.getLogger(MessageListener.class);

    @RabbitListener(queues = MessageConfig.QUEUE)
    public void listener(User user) {
        logger.info(String.format("Account %s has been created", user));
    }
}
