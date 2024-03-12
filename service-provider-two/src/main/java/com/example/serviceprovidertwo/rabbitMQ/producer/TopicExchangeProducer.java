package com.example.serviceprovidertwo.rabbitMQ.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicExchangeProducer {

    public static final String TOPIC_EXCHANGE_NAME = "TopicExchangeSpringBoot";
    public static final String TOPIC_ROUTING_KEY = "TopicExchangeRoutingKeySpringBoot.*";
    public static final String TOPIC_ROUTING_KEY1 = "TopicExchangeRoutingKeySpringBoot1.#";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void producer() {
        String msg = "MSG_TopicExchangeProducer";
            /*
                     投递消息的时候指定了交换机名称：就指定了交换机的类型，路由key ：根据交换机和队列的绑定关系交换机就可以将消息投递到对应的队列
                     */
        //参数：队列名称,RoutingKey消息内容（可以为可序列化的对象）
        this.rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_NAME, TOPIC_ROUTING_KEY, msg + "_" + TOPIC_ROUTING_KEY);
        this.rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_NAME, TOPIC_ROUTING_KEY1 + ".test", msg + "_" + TOPIC_ROUTING_KEY1);
        System.out.println("send: " + msg);
    }
}
