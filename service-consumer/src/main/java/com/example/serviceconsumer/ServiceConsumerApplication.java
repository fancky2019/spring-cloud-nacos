package com.example.serviceconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@EnableFeignClients//启用feign。微服务之间调用,服务发现
public class ServiceConsumerApplication {

    /*
    nacos的几个概念
    命名空间(Namespace)
    命名空间可用于进行不同环境的配置隔离。一般一个环境划分到一个命名空间
    配置分组(Group)
    配置分组用于将不同的服务可以归类到同一分组。一般将一个项目的配置分到一组
    配置集(Data ID)
    在系统中，一个配置文件通常就是一个配置集。一般微服务的配置就是一个配置集
     */

    /*
     1、在程序中 bootstrap.yaml文件中配置配置中心的信息
     2、将application.yaml中的配置信息在nacos的配置列表配置发布
        Data ID=applicationName+profile 如：service-consumer-test.yaml
     */
    public static void main(String[] args) {
        SpringApplication.run(ServiceConsumerApplication.class, args);
    }

}
