package com.example.gateway.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.gateway.utility.JWTUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Date;

//@Component
public class AuthenticationFilter implements GlobalFilter, Ordered {

    private static Logger logger = LogManager.getLogger(AuthenticationFilter.class);
    @Autowired
    private JWTUtility jwtUtility;

    /**
     * 过滤器
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //http://localhost:8080/gateway1/user?name=a
        ServerHttpRequest request = exchange.getRequest();
        //http://localhost:8080/gateway1/user?name=a
        String uri = request.getURI().toString();
        logger.info("接收请求:"+uri);
        //包含网关的前缀：/gateway1/user
        String path = exchange.getRequest().getURI().getPath();
        int index = path.indexOf('/', 1);

        //获取token
        String token = exchange.getRequest().getHeaders().getFirst("token");


        /*
         如果不需要token验证：
                             一、可以根据请求头中是否加入token来判断是否需要鉴权。
                             二、可以将需要验证的路由加入到服务器的配置文件中，
                                  请求到来时候，从配置中找该路径是否需要token，
                                  如果需要则验证，不需要则放过。这样可防止客户端token
                                  被恶意篡改。
         */
        //如果不想使用权限过滤器，将下面注释。
//        if(token==null||token=="")
//        {
//            return chain.filter(exchange);
//        }


        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        try {
            DecodedJWT decodedJWT = null;

            // 验证 token
            try {
                //不用校验自己手动解析
                decodedJWT = jwtUtility.verifier(token);
            } catch (TokenExpiredException e) {

                //在SpringbootProject项目中
                String loginUrl = "http://localhost:8101/user/login?name=fancky&password=pas";

                // httpServletResponse.addHeader("REDIRECT", "REDIRECT");//

                //不能用Location参数，自动重定向。浏览器接收的消息头中含有Location信息回自动重定向。
                //此时浏览器的地址框内的地址还是重定向之前的地址。
                //        httpServletResponse.addHeader("Location", loginUrl);//重定向地址
                serverHttpResponse.getHeaders().add("RedirectUrl", loginUrl);//重定向地址

                //1、POST方法重定向的使用场景太少，使得307状态码没有用武之地；2、GET方法虽然常需要使用的重定向，但使用302状态码也能正确运转
                serverHttpResponse.setStatusCode(HttpStatus.MOVED_TEMPORARILY);//http XMLHttpRequest.status!=200就报错：error。302告诉ajax这是重定向
                returnMsg(serverHttpResponse, "token is expired");
                return Mono.empty();
            } catch (JWTVerificationException e) {
                //返回状态码，前端根据装填码判断token 是过期。
                returnMsg(serverHttpResponse, "JWTVerificationException");
                return Mono.empty();
            }
            //获取Token中自定义信息
            //获取角色信息，此处可做角色权限判断控制。
            String role = decodedJWT.getClaim("role").asString();
            Date expireDate = decodedJWT.getClaim("expireDate").asDate();


            //从redis 中加载数据库配置的角色权限信息。判断当前用户的角色请求的路径是否权限符合
            //获取请求方法:
             String rolePath=path.substring(9,path.length());

            return chain.filter(exchange);
        } catch (Exception e) {
            String returnStr = "please login";
            return returnMsg(serverHttpResponse, returnStr);
        }

    }

    /**
     * 认证错误输出
     *
     * @param serverHttpResponse 响应对象
     * @param message            错误信息
     * @return
     */
    private Mono<Void> returnMsg(ServerHttpResponse serverHttpResponse, String message) {
//
////        serverHttpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
////        serverHttpResponse.getHeaders().add("Content-Type","application/json;charset=UTF-8");
//
//
//
//        String loginUrl = "http://localhost:8101/user/login?name=fancky&password=pas";
//
//        // httpServletResponse.addHeader("REDIRECT", "REDIRECT");//
//
//        //不能用Location参数，自动重定向。浏览器接收的消息头中含有Location信息回自动重定向。
//        //此时浏览器的地址框内的地址还是重定向之前的地址。
//        //        httpServletResponse.addHeader("Location", loginUrl);//重定向地址
//        serverHttpResponse.getHeaders().add("RedirectUrl", loginUrl);//重定向地址
//        serverHttpResponse.setStatusCode(HttpStatus.MOVED_TEMPORARILY);//http XMLHttpRequest.status!=200就报错：error。302告诉ajax这是重定向

        DataBuffer buffer = serverHttpResponse.bufferFactory().wrap(message.getBytes(StandardCharsets.UTF_8));
        return serverHttpResponse.writeWith(Flux.just(buffer));
    }


    @Override
    public int getOrder() {
        return -100;
    }

}
