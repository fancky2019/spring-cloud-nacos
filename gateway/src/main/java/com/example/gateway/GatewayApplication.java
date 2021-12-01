package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {
// #nacos将服务服务注册中心，默认采用应用程序名称。如果两个服务名称一样，网关会采用负载均衡。默认轮训 RoundRobinRule
    //负载均衡策略：（加权）轮训、随机
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
