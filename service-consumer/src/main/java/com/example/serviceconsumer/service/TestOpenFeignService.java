package com.example.serviceconsumer.service;

import com.example.serviceconsumer.model.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/*
    参见SpringCloud 项目
     微服务注册到注册中心的名称一样，网关路由转发会采用负载均衡
     #会用自带的负载均衡：http://localhost:8080/gateway1/user?name=a
      # 客户端负载均衡实现：两个微服务注册到注册中心的名称一样，ip和端口不一样
 */


//@FeignClient(name = "feignClientTest", url = "${sbp.ordermigratedbtoolurl}") springboot
//@FeignClient(value = "server", fallbackFactory = UserServiceFallBackFactory.class)//开启回调
@FeignClient(value = "${service.provider-one}", fallbackFactory = UserServiceFallBackFactory.class)//开启回调
public interface TestOpenFeignService {

    /*
      Client 接口默认方法execute
      HttpURLConnection connection = this.convertAndSend(request, options);

      OkHttpClient
      execute
     */
    
    @GetMapping(value = "/test/helloWorld")
   String helloWorld(@RequestParam String hello) throws Exception;

    @PostMapping("/test/add")
    Student add(@RequestBody Student student);


//    @PostMapping("/test/addUser1")
//    String addUser1(@RequestBody @Validated UserInfo request, @RequestHeader("token") String token);



}
