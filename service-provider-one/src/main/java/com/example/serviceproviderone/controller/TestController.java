package com.example.serviceproviderone.controller;

import com.example.serviceproviderone.model.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/test")
public class TestController {

    private static Logger logger = LogManager.getLogger(TestController.class);

    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${server.port}")
    private String port;

    @GetMapping("/helloWorld")
    public String helloWorld(String hello)
    {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");
        String dateStr = dateTimeFormatter.format(LocalDateTime.now());
        logger.info(dateStr);
        return dateStr+":"+applicationName+":"+ port +"-"+hello;
    }

    @PostMapping("/add")
    public Student add(@RequestBody  Student student)
    {
        String msg="name:"+ student.getName()+";age:"+student.getAge();
        student.setName( applicationName+":"+ port +"-"+student.getName());
        return student;
    }



}
