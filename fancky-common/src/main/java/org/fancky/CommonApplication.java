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
 *
 *
 */