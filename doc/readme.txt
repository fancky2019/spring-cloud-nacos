配置文件优先级(由高到低):
bootstrap.properties -> bootstrap.yml -> application.properties -> application.yml





nacos:
环境准备：
下载：https://nacos.io/zh-cn/docs/quick-start.html
解压：tar -zxvf nacos-server-1.4.3.tar.gz
win bin目录启动单例模式：startup.cmd -m standalone
集群模式:sh startup.sh
高可用集群：最少主备模式（2台），使用外置数据源mysql 配置保存高可用信息


配置管理控制台：
http://localhost:8848/nacos/#/login
用户名：nacos 密码:nacos

v2.2.3
控制台访问：
http://localhost:8848/nacos

配置：
 1、在程序中 bootstrap.yaml文件中配置配置中心的信息
 2、将application.yaml中的配置信息在nacos的配置列表配置发布
    Data ID=applicationName+profile 如：service-consumer-test.yaml
    注：配置文件带.yaml后缀。

启动微服务
idea: view--Tool Windows--services(Alt+8)
添加--Run Configuration Type--Spring Boot
debug对应的服务

环境依赖：
nacos、redis（gateway依赖)

服务启动不分先后

测试用例见postman nacos