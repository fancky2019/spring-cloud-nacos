package com.example.serviceproviderone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceProviderOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceProviderOneApplication.class, args);
    }

}
