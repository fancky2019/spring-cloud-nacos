package com.example.serviceproviderone.controller;

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
