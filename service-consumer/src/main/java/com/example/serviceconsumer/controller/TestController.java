package com.example.serviceconsumer.controller;

import com.example.serviceconsumer.service.TestOpenFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@RefreshScope   //@RefreshScope 配置自动更新
@RestController
@RequestMapping("/test")
public class TestController {

//    private final RestTemplate restTemplate;

    @Autowired
    private TestOpenFeignService testOpenFeignService;

//    @Autowired
//    public TestController(RestTemplate restTemplate, TestOpenFeignService testOpenFeignService) {
//
//        this.restTemplate = restTemplate;
//        this.testOpenFeignService = testOpenFeignService;
//    }

    @Value("${config.appName}")
    private String appName;

    @GetMapping("/helloWorld")
    public String helloWorld(String hello) {


        String jsonStr = testOpenFeignService.helloWorld("test");
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

        String jsonStr = testOpenFeignService.helloWorld("test");
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

}
