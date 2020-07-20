package com.mybatistest;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

//抽取了SqlSessionFactory工具类，用来生成sqlSessionFactory，省去写重复代码
public class SqlSessionFactoryUtils {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            //创建SqlSessionFactoryBuilder对象
            SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
            //创建核心配置文件的输入流
            InputStream inputStream = Resources.getResourceAsStream("SQLMapConfig.xml");
            //通过输入流创建SqlSessionFactory对象
            sqlSessionFactory = ssfb.build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}