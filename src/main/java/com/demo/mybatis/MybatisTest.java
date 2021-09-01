package com.demo.mybatis;

import com.demo.mybatis.domain.TRole;
import com.demo.mybatis.mapper.RoleMapper;
import com.demo.mybatis.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

/**
 * @version V1.0
 * @Title:
 * @Package
 * @Description:
 * @author: 李钢 2580704698@qq.com
 * @date: 2021/7/29 23:19
 * @Copyright: Copyright (c) 2019
 */
public class MybatisTest {
    private static Logger logger = Logger.getLogger(MybatisTest.class);

    public static void main(String[] args) {
        doTest2();
    }

    public static void doTest1() {
        String resource = "mybatis-config.xml";
        try {

            InputStream inputStream = Resources.getResourceAsStream(resource);
            //SqlSessionFactoryBuilder（构造器）：它会根据配置或者代码来生成SqlSessionFactory，采用的是分步构建的Builder模式。
            //SqlSessionFactory（工厂接口）：依靠它来生成SqlSession，使用的是工厂模式。
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            //SqlSession（会话）：一个既可以发送SQL执行返回结果，也可以获取Mapper的接口。在现有的技术中，一般我们会让其在业务逻辑代码中“消失”，而使用的是MyBatis提供的SQL Mapper接口编程技术，它能提高代码的可读性和可维护性。
            SqlSession sqlSession = sqlSessionFactory.openSession();

            //SQL Mapper（映射器）：MyBatis新设计存在的组件，它由一个Java接口和XML文件（或注解）构成，需要给出对应的SQL和映射规则。它负责发送SQL去执行，并返回结果。
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            TRole role = roleMapper.getRole(1L);
            System.out.println(role.toString());
            TRole role2 = roleMapper.getRole2(1L);
            System.out.println(role2.toString());

        } catch (IOException e) {
            logger.error("{}", e);
        }
    }

    public static void doTest2() {
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            //通过SqlSession获取了一个RoleMapper接口对象
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            //通过getRole方法获取对象
            TRole role = roleMapper.getRole(1L);
            logger.info(role.getRoleName());
        } finally {
            if (sqlSession != null) {
                //正确关闭SqlSession对象
                sqlSession.close();
            }
        }
    }

}
