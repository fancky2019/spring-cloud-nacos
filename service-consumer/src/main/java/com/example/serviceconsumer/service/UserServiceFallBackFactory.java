package com.example.serviceconsumer.service;

/*
 框架2.1.1升级到2.5.4
 变更记录：feign.hystrix.FallbackFactory--->org.springframework.cloud.openfeign.FallbackFactory;
 */

import com.example.serviceconsumer.model.MessageResult;
import com.example.serviceconsumer.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/*
如果页面显示异常信息，说明熔断没有开启成功
成功：返回UserServiceFallBackFactory的返回值
 */
@Slf4j
@Component
public class UserServiceFallBackFactory implements FallbackFactory<TestOpenFeignService> {



    @Override
    public TestOpenFeignService create(Throwable throwable) {
//        return (name) ->
//        {
//            String errorMessage = throwable.getMessage();
//            return "FeignClient微服务调用熔断：返回异常默认值";
//        };

        return new TestOpenFeignService() {
            @Override
            public String helloWorld(String hello) throws Exception {
                log.error(throwable.getMessage());
                //throw new Exception( "异常超时熔断");
                return  "异常超时熔断";
            }

            @Override
            public Student add(Student student) {
                 // mq 加入补偿
                log.error(throwable.getMessage());
                return student;
            }





        };

    }

}