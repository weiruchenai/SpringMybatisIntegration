package com.springmybatis;

import com.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "userDao2_1")
public interface UserDao2_1 {
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
