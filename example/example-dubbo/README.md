执行 mvn install, maven会自动将source install到repository.
执行 mvn deploy, maven会自动将source deploy到remote-repository.
执行 mvn source:jar, 单独打包源码.

1. 2015-10-15(周四)，构建example-dubbo工程.

测试方法:
1). 修改配置文件resources/dubbo-consumer.xml、resources/dubbo-provider.xml
    主要配置zookeeper地址：<dubbo:registry address="zookeeper://w22.node:2181" />
2). 启动服务.   ServerProvider.main()
3). 客户端调用.  ClientConsumer.main()

