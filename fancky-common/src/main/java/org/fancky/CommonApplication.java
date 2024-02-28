//package org.fancky;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
///**
// * 公共模块不需要启动，不需要主类，打包需要在pom中设置
// */
//@SpringBootApplication
//public class CommonApplication {
//    public static void main(String[] args) {
//        SpringApplication.run(CommonApplication.class, args);
//    }
//
//}

/**
 *
 * 公共模块的配置类要写在spring.factories 文件中
 *1、 @Import 导入配置类
 * 2、在spring.factories 配置配置类
 *3、pom 没有主类，需要设置打包
 *
 */

/**
 1、定义字段配置文件类
 2、定义自动配置类：启用配置属性加入IOC、starter业务类通过bean加入IOC
 3、添加spring.factories文件配置自动装配
 4、pom配置无main类启动 install到本地仓库
 5、其他工程引入依赖
 */