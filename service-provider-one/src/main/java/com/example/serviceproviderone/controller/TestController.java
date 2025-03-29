package com.example.serviceproviderone.controller;

import com.baomidou.mybatisplus.extension.plugins.inner.DataChangeRecorderInnerInterceptor;
import com.example.serviceproviderone.model.MqMessage;
import com.example.serviceproviderone.model.Student;
import com.example.serviceproviderone.rabbitMQ.RabbitMQConfig;
import com.example.serviceproviderone.rabbitMQ.producer.DirectExchangeProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.TraceContext;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    private static Logger logger = LogManager.getLogger(TestController.class);

    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${server.port}")
    private String port;

    @Autowired
    private DirectExchangeProducer directExchangeProducer;




    /**
     * sleuth 实现了traceId spanId,nacos 、consumer、provider 内部的
     * 每个服务 traceId 一样 。服务间spanId 不一样。
     * sleuth 不会在xxl-job中生成traceId 和spanId
     *
     *
     *
     * Trace ID是调用链的全局唯一标识符.每个服务一个spanId
     * 发生熔断之后，调用方不会收到服务方的返回消息
     *
     *
     *
     *
     * @param hello
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/helloWorld")
    public String helloWorld(String hello) throws InterruptedException {
        log.info("链路跟踪测试{}",hello);
        Thread.sleep(20000);
//        int m=Integer.parseInt("ds");//异常不会进入熔断，超时熔断
         DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");
        String dateStr = dateTimeFormatter.format(LocalDateTime.now());
        logger.info(dateStr);
        return "熔断测试："+ dateStr+":"+applicationName+":"+ port +"-"+hello;
    }

    @PostMapping("/add")
    public Student add(@RequestBody  Student student)
    {
        String msg="name:"+ student.getName()+";age:"+student.getAge();
        student.setName( applicationName+":"+ port +"-"+student.getName());
        return student;
    }

    @GetMapping("/sleuthTraceId")
    public String sleuthTraceId(HttpServletRequest httpServletRequest)
    {
        TraceContext traceContext = (TraceContext) httpServletRequest.getAttribute(TraceContext.class.getName());
        String traceId =  traceContext.traceId();
        String spanId = traceContext.spanId();
        log.info("1链路跟踪测试{}",traceId);
        MqMessage mqMessage = new MqMessage
                (RabbitMQConfig.BATCH_DIRECT_EXCHANGE_NAME,
                        RabbitMQConfig.BATCH_DIRECT_ROUTING_KEY,
                        RabbitMQConfig.BATCH_DIRECT_QUEUE_NAME,
                        traceId);
        directExchangeProducer.produceNotConvertSent(mqMessage);
        log.info("2链路跟踪测试{}",traceId);
        return "student";
    }

}
