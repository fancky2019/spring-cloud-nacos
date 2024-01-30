package com.example.serviceprovidertwo.controller;

import com.example.serviceprovidertwo.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/configName")
    public String configName(String name)
    {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");
        String dateStr = dateTimeFormatter.format(LocalDateTime.now());
        logger.info(dateStr);
        return dateStr+":"+applicationName+":"+ port +"-";
    }
    @GetMapping("/helloWorld")
    public String helloWorld(String hello)
    {
        log.info("链路跟踪测试{}",hello);
        return applicationName+":"+ port +"-"+hello;
    }

    @PostMapping("/add")
    public Student add(@RequestBody Student student)
    {
        String msg="name:"+ student.getName()+";age:"+student.getAge();
        student.setName( applicationName+":"+ port +"-"+student.getName());
        return student;
    }

}
