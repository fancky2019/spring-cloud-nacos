package com.example.serviceprovidertwo.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggerAspectj {
//    private static final Logger LOGGER = LoggerFactory.getLogger("AppAspect");
    private static Logger LOGGER = LogManager.getLogger(LoggerAspectj.class);








    ////    @Pointcut("execution(* com.example.serviceproviderone.controller.*.*(..)) || execution(* com.onlyedu.online.controller.market.*.*(..))")
//   @Pointcut("execution(* com.example.serviceprovidertwo.controller.*.*(..))")
//    public void pointCut() {
//
//    }
//
//
//    @Around("pointCut()")
//    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
//        String className = joinPoint.getTarget().getClass().toString();
//        String methodName = joinPoint.getSignature().getName();
//        Object[] args = joinPoint.getArgs();
//
////        Gson gson = new Gson();
////        LOGGER.info("{}#{} 开始处理, 参数列表 : {}", className, methodName, gson.toJson(args));
////
//
//        LOGGER.info("{}#{} 开始处理, 参数列表", className, methodName);
//        Object proceed = joinPoint.proceed();
//        LOGGER.info("{}#{} 处理完成", className, methodName);
//
//        return proceed;
//    }
//
//
//    @AfterThrowing(pointcut = "pointCut()", throwing = "ex")
//    public void onExceptionThrow(Exception ex) {
//        LOGGER.info("",ex);
//    }
//



    /**
     * 环绕增强：目标方法执行前后分别执行一些代码，发生异常的时候执行另外一些代码
     *
     * @return
     */
    @Around(value = "execution(* com.example.serviceprovidertwo.controller.*.*(..))")
    public Object aroundMethod(ProceedingJoinPoint jp) throws Throwable {
        String methodName = jp.getSignature().getName();
        Object result = null;
//        try {
            LOGGER.info("【环绕增强中的--->前置增强】：the method 【" + methodName + "】 begins with " + Arrays.asList(jp.getArgs()));
            //执行目标方法
            result = jp.proceed();
            LOGGER.info("【环绕增强中的--->返回增强】：the method 【" + methodName + "】 ends with " + result);
//        } catch (Throwable e) {
//            result = "error";
//            LOGGER.info("【环绕增强中的--->异常增强】：the method 【" + methodName + "】 occurs exception " + e);
//        }
        LOGGER.info("【环绕增强中的--->后置增强】：-----------------end.----------------------");
        return result;
    }




}
