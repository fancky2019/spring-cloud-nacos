package com.example.gateway.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.support.DefaultServerCodecConfigurer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/*
如果网关（spring cloud gateway ）设置了跨域，下游微服务就不要设置跨域，否则报下面的错误。
gateway.html:1 Access to XMLHttpRequest at 'http://localhost:8080/gateway/springBootProject/jwt/authorise?_=1574837722367'
from origin 'http://localhost:63342' has been blocked by CORS policy: The 'Access-Control-Allow-Origin' header contains multiple values
 'http://localhost:63342, http://localhost:63342', but only one is allowed.
 */

/*
CorsWebFilter
 */
@Configuration
public class CorsFilterConfig {

    // private static final String MAX_AGE = "18000L";

//    @Bean
//    public WebFilter corsFilter() {
//        return (ServerWebExchange ctx, WebFilterChain chain) -> {
//            ServerHttpRequest request = ctx.getRequest();
//            if (CorsUtils.isCorsRequest(request)) {
//                HttpHeaders requestHeaders = request.getHeaders();
//                ServerHttpResponse response = ctx.getResponse();
//                HttpMethod requestMethod = requestHeaders.getAccessControlRequestMethod();
//                HttpHeaders headers = response.getHeaders();
//                headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, requestHeaders.getOrigin());
//               // headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS,"Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
//                headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS,"*");
//                headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
//                headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS,"*");
//                //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）,不然前端获取不到头部信息
////                headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*");
//
//                headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "token");
//                headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "RedirectUrl");
//                headers.add(HttpHeaders.ACCESS_CONTROL_MAX_AGE, MAX_AGE);
//                if (request.getMethod() == HttpMethod.OPTIONS) {
//                    response.setStatusCode(HttpStatus.OK);
//                    return Mono.empty();
//                }
//            }
//
//            return chain.filter(ctx);
//        };
//    }

    /**
     * 配置跨域
     *
     * 在头部配置：
     *     add_header： Access-Control-Allow-Origin *;
     *     add_header： Access-Control-Allow-Methods 'GET, POST, OPTIONS';
     *     add_header： Access-Control-Allow-Headers 'DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Authorization';
     *
     *
     *Access-Control-Allow-Credentials: true
     * @return
     */
    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // cookie跨域
        config.setAllowCredentials(Boolean.TRUE);
        config.addAllowedMethod("*");
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        // 配置前端js允许访问的自定义响应头
        //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）,不然前端获取不到头部信息
        config.addExposedHeader("token");
        config.addExposedHeader("RedirectUrl");
        config.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }


}
