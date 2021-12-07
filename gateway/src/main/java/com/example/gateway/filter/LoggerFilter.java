package com.example.gateway.filter;

import com.example.gateway.filter.AuthenticationFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

/**
实现Ordered接口，否则不能进入返回结果回调
 */

@Component
public class LoggerFilter implements GlobalFilter, Ordered {
    private static Logger log = LogManager.getLogger(LoggerFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String method = request.getMethodValue();

        if (HttpMethod.POST.matches(method)) {
            return DataBufferUtils.join(exchange.getRequest().getBody())
                    .flatMap(dataBuffer -> {
                        byte[] bytes = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(bytes);
                        String bodyString = new String(bytes, StandardCharsets.UTF_8);
                        logtrace(exchange, bodyString);
                        exchange.getAttributes().put("POST_BODY", bodyString);
                        DataBufferUtils.release(dataBuffer);


                        Flux<DataBuffer> cachedFlux = Flux.defer(() -> {
                            DataBuffer buffer = exchange.getResponse().bufferFactory()
                                    .wrap(bytes);
                            return Mono.just(buffer);
                        });

                        ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(
                                exchange.getRequest()) {
                            @Override
                            public Flux<DataBuffer> getBody() {
                                return cachedFlux;
                            }
                        };



                        StringBuilder stringBuilder = new StringBuilder();
                        ServerHttpResponse response = exchange.getResponse();
                        DataBufferFactory bufferFactory = response.bufferFactory();
                        stringBuilder.append("Response Info:");
                        ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(response) {
                            @Override
                            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                                if (body instanceof Flux) {
                                    Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;
                                    return super.writeWith(fluxBody.map(dataBuffer -> {
                                        // probably should reuse buffers
                                        byte[] content = new byte[dataBuffer.readableByteCount()];
                                        dataBuffer.read(content);
                                        DataBufferUtils.release(dataBuffer);
                                        String responseResult = new String(content, Charset.forName("UTF-8"));
                                        stringBuilder.append("status=").append(this.getStatusCode());
                                        //stringBuilder.append(";header=").append(this.getHeaders());
                                        stringBuilder.append(";responseResult=").append(responseResult);
                                        log.info(stringBuilder.toString());
                                        return bufferFactory.wrap(content);
                                    }));
                                }
                                return super.writeWith(body); // if body is not a flux. never got there.
                            }
                        };

                        return chain.filter(exchange.mutate().request(mutatedRequest).response(decoratedResponse).build());
                    });
        } else if (HttpMethod.GET.matches(method)) {
            Map m = request.getQueryParams();
            logtrace(exchange, m.toString());
        }

        return chain.filter(exchange);
    }

    /**
     * 日志信息
     *
     * @param exchange
     * @param param    请求参数
     */
    private void logtrace(ServerWebExchange exchange, String param) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        String path = serverHttpRequest.getURI().getPath();
        String method = serverHttpRequest.getMethodValue();
        String headers = serverHttpRequest.getHeaders().entrySet()
                .stream()
                .map(entry -> "            " + entry.getKey() + ": [" + String.join(";", entry.getValue()) + "]")
                .collect(Collectors.joining("\n"));
        log.info("Request : \n" +
                        "HttpMethod : {}\n" +
                        "Uri        : {}\n" +
                        "Param      : {}\n" +
                        "Headers    : \n" +
                        "{}\n"
                , method, path, param, headers);
    }


    @Override
    public int getOrder() {
        return -2;
    }

}