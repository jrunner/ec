package com.t2t.examples;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

/**
 * 最简单的调用方法
 */
public class Simple {
    public static String resource = "mybatis.xml";

    public static void main(String[] args) throws IOException {
        //构建sqlSession的工厂
        SqlSessionFactory sessionFactory = getFactory1();
        //创建能执行映射文件中sql的sqlSession
        SqlSession session = sessionFactory.openSession();

        //mapping.userMapper: 是userMapper.xml文件中mapper标签的namespace属性的值
        //getUser: select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
        System.out.println("1. 执行查询返回一个唯一user对象的sql");
        User user = session.selectOne("mapping.userMapper.getUser", 1);
        System.out.println(user.getName() + "," + user.getAge());

        System.out.println("2. 执行查询返回列表");
        List<User> list = session.selectList("mapping.userMapper.listUser");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getName() + "," + list.get(i).getAge());
        }
    }

    //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
    public static SqlSessionFactory getFactory1() throws IOException {
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        return sessionFactory;
    }

    //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
    public static SqlSessionFactory getFactory2() throws IOException {
        InputStream is = Simple.class.getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        return sessionFactory;
    }
}