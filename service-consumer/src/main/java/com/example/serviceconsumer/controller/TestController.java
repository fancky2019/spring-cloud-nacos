package com.example.serviceconsumer.controller;

import com.example.serviceconsumer.model.Student;
import com.example.serviceconsumer.service.TestOpenFeignService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@RefreshScope   //@RefreshScope 配置自动更新
@RestController
@RequestMapping("/test")
public class TestController {

    private static Logger logger = LogManager.getLogger(TestController.class);

//    private final RestTemplate restTemplate;

    @Autowired
    private TestOpenFeignService testOpenFeignService;

//    @Autowired
//    public TestController(RestTemplate restTemplate, TestOpenFeignService testOpenFeignService) {
//
//        this.restTemplate = restTemplate;
//        this.testOpenFeignService = testOpenFeignService;
//    }

    //在nacos中修改配置，发布。再次调用发现配置更新。
    @Value("${config.appName}")
    private String appName;

    @GetMapping("/helloWorld")
    public String helloWorld(String hello) {

        logger.info("hello="+hello);
        String jsonStr =appName +":"+ testOpenFeignService.helloWorld("test");
        int m = 0;
        return jsonStr;
//        try {
//            return restTemplate.getForObject("http://service-provider-one/test/helloWorld?hello=" + hello, String.class);
//        } catch (RestClientException e) {
//            e.printStackTrace();
//            return "";
//        }

    }


    @GetMapping("/configTest")
    public String configTest(String hello) {

        String jsonStr =appName +":"+ testOpenFeignService.helloWorld("test");
        int m = 0;
        try {
            //设置超时熔断
            Thread.sleep(10*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return appName + ":" + jsonStr;
//        try {
//            return restTemplate.getForObject("http://service-provider-one/test/helloWorld?hello=" + hello, String.class);
//        } catch (RestClientException e) {
//            e.printStackTrace();
//            return "";
//        }

    }

    @PostMapping("/add")
    public Student add(@RequestBody Student student)
    {
        Student newStudent=  testOpenFeignService.add(student);
        return newStudent;
    }

}
