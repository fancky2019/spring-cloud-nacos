package com.example.serviceprovidertwo;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;


//@ComponentScan(basePackages = {"org.fancky" })
//@SecurityScheme(name = "api_token", type = SecuritySchemeType.HTTP, scheme ="bearer", in = SecuritySchemeIn.HEADER)
@SpringBootApplication
@EnableDiscoveryClient
public class ServiceProviderTwoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceProviderTwoApplication.class, args);
    }

}
