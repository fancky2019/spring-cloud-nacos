package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    //nginx负载均衡策略：（加权）轮训、随机、ip hash、最小连接数
    //请求经过nginx负载均衡进入注册中心的网关，网关在根据负载均衡调用注册中心的服务。

    //nginx 服务端负载均衡
    //ribbon 客户端负载均衡

// #nacos将服务服务注册中心，默认采用应用程序名称。如果两个服务名称一样，网关会采用负载均衡。默认轮训 RoundRobinRule
    //负载均衡策略：（加权）轮训、随机


    //1、在网关设计认证：网关作为oauth2 client  + sso server 处理，
    //2、授权不在sso,授权设计在具体的资源服务中

   /* 认证授权处理
    1、如果自己写可以把认证授权逻辑集成在网关里，但是网关还要处理白名单等业务处理，最好别再网关做sso.
    2、网关只负责转发,认证放在sso处理。资源微服务和sso负责处理鉴权。简单
    3、网关作为oauth2 client  + sso server 处理。避免每个微服务都坐鉴权处理
    4、授权不在sso,授权设计在具体的资源服务中
    */

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
