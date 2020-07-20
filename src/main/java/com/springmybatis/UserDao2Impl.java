package com.springmybatis;

import com.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class UserDao2Impl extends SqlSessionDaoSupport implements UserDao2{

    @Override
    public User getUserById(Integer id) {
        SqlSession sqlSession = super.getSqlSession();
        //查询用户并返回
        User user = sqlSession.selectOne("UserDao2.getUserById", id);
        return user;
    }

    @Override
    public List<User> getUserByName(String userName) {
        return super.getSqlSession().selectOne("UserDao2.getUserByName", userName);
    }

    @Override
    public void insertUser(User user) {
        super.getSqlSession().insert("UserDao2.InsertUser", user);
    }
}
