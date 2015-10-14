mybatis简单介绍.
mybatis的前身叫iBatis,是apache的一个开源项目.
mybatis使用简单的xml文件或者注解配置映射.
mybatis入门上手非常快,易学易用,是开发项目的一个不错的选择.

1.用maven构建项目.
   a.生成项目框架:mvn archetype:generate.
   b.修改pom文件,将jar为war,并加入第三方包依赖.
   c.构建项目:mvn eclipse:myeclipse-clean eclipse:myeclipse.
   d.导入项目到ide:myeclipse.

2.创建model和sql.
   a.创建User和UserType类.
      如果创建UserType枚举类报错,修改项目的编译环境由1.4改为1.6.
   b.创建src/main/resources
      添加user.sql文件.
  
3.mybatis测试.
   配置文件里面涉及到一些参数,先不做分析.
    
4.初始化spring+mybatis+log4j环境.
   a.创建service和mapper包,添加UserMapper.xml配置文件.
      在service层添加注解,其中@Transactional(rollbackFor = Exception.class)意思是如果发生异常了事务进行回滚.
      在service层不能捕获Exception,因为如果捕获了Exception,事务就不能回滚了,解决方法是捕获Exception后手动抛出异常,throw new RuntimeException("事务回滚");
   b.添加spring.xml配置文件:dataSource用BoneCPDataSource的默认配置.
   c.添加mybatis.xml配置文件.
   d.添加log4j.xml配置文件:修改日志保存路径.
   e.添加测试类,测试集成环境.
   
5.增删改查和事务测试.
   一些涉及到底层的操作先不做分析.
   
6.搭建springmvc+spring+mybatis环境.
  a.添加springmvc配置文件.
  b.添加UserController.
  c.修改UserServiceImpl.
  d.修改web.xml配置文件.
  e.启动服务.
  
7.测试Controller.

8.增加新的数据表,实现关联查询.
   添加UserSay.
************************
9.多数据源使用.


10.mybatis注解.


11.底层源码分析.
   

