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

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
