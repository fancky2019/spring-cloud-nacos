package com.example.serviceconsumer.service;

import com.example.serviceconsumer.model.Student;
import feign.okhttp.OkHttpClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/*
    参见SpringCloud 项目
     微服务注册到注册中心的名称一样，网关路由转发会采用负载均衡
     #会用自带的负载均衡：http://localhost:8080/gateway1/user?name=a
      # 客户端负载均衡实现：两个微服务注册到注册中心的名称一样，ip和端口不一样
 */


//@FeignClient(name = "feignClientTest", url = "${sbp.ordermigratedbtoolurl}") springboot
//@FeignClient(value = "server", fallbackFactory = UserServiceFallBackFactory.class)//开启回调
@FeignClient(value = "${service.provider-one}")//开启回调
public interface TestOpenFeignService {

    /*
      Client 接口默认方法execute
      HttpURLConnection connection = this.convertAndSend(request, options);

      OkHttpClient
      execute
     */
    @GetMapping(value = "/test/helloWorld")
    String helloWorld(@RequestParam String hello);

    @PostMapping("/test/add")
    Student add(@RequestBody Student student);


//    @PostMapping("/test/addUser1")
//    String addUser1(@RequestBody @Validated UserInfo request, @RequestHeader("token") String token);



}
