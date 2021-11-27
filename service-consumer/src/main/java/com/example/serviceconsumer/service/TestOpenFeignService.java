package com.example.serviceconsumer.service;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


//@FeignClient(name = "feignClientTest", url = "${sbp.ordermigratedbtoolurl}") springboot
//@FeignClient(value = "server", fallbackFactory = UserServiceFallBackFactory.class)//开启回调
@FeignClient(value = "${service.provider-one}")//开启回调
public interface TestOpenFeignService {

    @RequestMapping(value = "/test/helloWorld")
    String helloWorld(@RequestParam String hello);
}
