package com.example.gateway.configuration;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

/*
Redis 限流：别忘记启动Redis
 */
@Configuration
public class RequestRateLimiterByRedisConfig {

    /*
    IP
     */
    @Bean(value = "ipKeyResolver")
    public KeyResolver ipKeyResolver() {
        //获取访问者的ip地址, 通过访问者ip地址进行限流, 限流使用的是Redis中的令牌桶算法
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
    }

//    /*
//    用户
//     */
//    @Bean(value = "userKeyResolver")
//    KeyResolver userKeyResolver() {
//        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("userId"));
//    }
//
//    /*
//    接口
//     */
//    @Bean(value = "apiKeyResolver")
//    KeyResolver apiKeyResolver() {
//        return exchange -> Mono.just(exchange.getRequest().getPath().value());
//    }
}
