package com.springmybatis;

import com.pojo.User;

import java.util.List;

public interface UserDao2 {
    /**
     * 根据id查询用户
     * @param id 用户id
     * @return User 查询到的用户
    */
    User getUserById(Integer id);

    /**
     * 根据用户名查询用户
     * @param: String 用户名
     * @return: List<User>用户名列表
    */
    List<User> getUserByName(String userName);

    /**
     * 插入用户
     * @param: User
    */
    void insertUser(User user);
}
