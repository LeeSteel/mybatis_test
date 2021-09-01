package com.demo.mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;


/**
 * @version V1.0
 * @Title:
 * @Package
 * @Description: 使用mybatis-config.xml文件，通过SQLSessionFactory-Builder来构建SqlSessionFactory。由于SqlSessionFactory应该采用单例模式，所以这里使用单例模式去构建它
 * @author: 李钢 2580704698@qq.com
 * @date: 2021/7/29 23:44
 * @Copyright: Copyright (c) 2019
 */
public class SqlSessionFactoryUtil {

    private final static Class<SqlSessionFactoryUtil> LOCK = SqlSessionFactoryUtil.class;
    private static SqlSessionFactory SqlSessionFactory = null;

    /**
     * 构造方法中加入了private关键字，使得其他代码不能通过new的方式来创建它。
     */
    private SqlSessionFactoryUtil() {
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        //加入synchronized关键字加锁，主要是为了防止在多线程中多次实例化SqlSessionFactory对象，从而保证SqlSessionFactory的唯一性。
        synchronized (LOCK) {
            if (SqlSessionFactory != null) {
                return SqlSessionFactory;
            }
            String resource = "mybatis-config.xml";
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream(resource);
                SqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return SqlSessionFactory;
        }
    }

    /**
     * openSqlSession方法的作用则是创建SqlSession对象。
     *
     * @return
     */
    public static SqlSession openSqlSession() {
        if (SqlSessionFactory == null) {
            getSqlSessionFactory();
        }
        return SqlSessionFactory.openSession();
    }
}
