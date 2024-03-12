package com.example.serviceproviderone.rabbitMQ;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RabbitMqManager {

    @Resource
    RabbitAdmin rabbitAdmin;

    /**
     * 获取对应队列的数量;
     *
     * @param queue
     * @return
     */
    public int getMessageCount(String queue) {
        AMQP.Queue.DeclareOk declareOk = rabbitAdmin.getRabbitTemplate().execute(channel -> channel.queueDeclarePassive(queue));
        return declareOk.getMessageCount();
    }


}
