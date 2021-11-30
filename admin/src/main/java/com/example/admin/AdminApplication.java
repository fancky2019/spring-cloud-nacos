package com.example.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableAdminServer  //springbootadmin
@EnableDiscoveryClient
@SpringBootApplication
public class AdminApplication {

    /*
       地址：http://localhost:8202/applications
       微服务启动了，admin同步信息有延迟
     */

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

}
