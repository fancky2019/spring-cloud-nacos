package com.example.gateway.configuration;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

/**
Redis 限流：别忘记启动Redis
 */
@Configuration
public class RequestRateLimiterByRedisConfig {

    /*
    限流算法：
    计数器：每秒重置次数
    漏铜：恒定速率。不能解决突然加大流量。强行限制数据的传输速率。
    令牌桶：从令牌桶里取令牌。令牌桶容量大于等于取令牌速率。可以解决临时大流量。超过令牌桶容量拒绝。
     */

    /*
    @Primary 多个相同的bean用@Primary指定默认
     */


    /*
    一般采用路径限流，避免大量用户访问同一个服务。尤其是抢购场景。
     */



    /**
    根据用户IP限流
     */
    @Bean(value = "ipKeyResolver")
    public KeyResolver ipKeyResolver() {
        //获取访问者的ip地址, 通过访问者ip地址进行限流, 限流使用的是Redis中的令牌桶算法
        return exchange -> reactor.core.publisher.Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
    }

    /**
    根据用户限流
     */
    @Bean(value = "userKeyResolver")
    KeyResolver userKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("userId"));
    }

    /**
    根据后端接口限流
     */
    @Bean(value = "pathKeyResolver")
    @Primary
    KeyResolver pathKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getPath().value());
    }
}
