package com.example.serviceconsumer.service.api.wms;


import com.example.serviceconsumer.model.wms.ShipOrderInventoryDetailDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;

/*
 框架2.1.1升级到2.5.4
 变更记录：feign.hystrix.FallbackFactory--->org.springframework.cloud.openfeign.FallbackFactory;
 */

/*
如果页面显示异常信息，说明熔断没有开启成功
成功：返回UserServiceFallBackFactory的返回值
 */
@Component
@Slf4j
public class WmsServiceFallbackFactoryr implements FallbackFactory<WmsService> {

//    private static Logger logger = LogManager.getLogger(WmsServiceFallbackFactoryr.class);

    @Override
    public WmsService create(Throwable throwable) {
//        return (name) ->
//        {
//            String errorMessage = throwable.getMessage();
//            return "FeignClient微服务调用熔断：返回异常默认值";
//        };

        return new WmsService() {

            @Override
            public String completeShipOrder( BigInteger shipOrderId, String token) {
                //
                System.out.println(throwable.getMessage());
                log.error(throwable.getMessage());
                return "返回异常默认值";
            }

            @Override
            public String shipOrderTest(@RequestParam String test) {
                System.out.println(throwable.getMessage());
                log.error(throwable.getMessage());
                return "0";
            }

            @Override
            public boolean checkRelation(ShipOrderInventoryDetailDto query, String token) {
                return false;
            }

        };

    }

}