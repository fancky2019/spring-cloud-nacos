package com.example.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
处理熔断回调
 */
@RestController
@RequestMapping("/servercallback")
public class ServerCallBackController {

    @GetMapping("/defaultfallback")
    public String microService1(String name) {
        return "网关熔断：服务不可以用";
    }

    @GetMapping("/springbootproject")
    public String springBootProject(String name) {
        return "网关熔断：springBootProject 不可用";
    }
}
