配置文件优先级(由高到低):
bootstrap.properties -> bootstrap.yml -> application.properties -> application.yml





nacos:
环境准备：
下载：https://nacos.io/zh-cn/docs/quick-start.html
解压：tar -zxvf nacos-server-1.4.3.tar.gz
bin目录启动：startup.cmd -m standalone

配置管理控制台：
http://localhost:8848/nacos/#/login
用户名：nacos 密码:nacos

配置：
 1、在程序中 bootstrap.yaml文件中配置配置中心的信息
 2、将application.yaml中的配置信息在nacos的配置列表配置发布
    Data ID=applicationName+profile 如：service-consumer-test.yaml
    注：配置文件带yaml后缀。

启动微服务
idea: view--Tool Windows--services(Alt+8)
添加--Run Configuration Type--Spring Boot
debug对应的服务
