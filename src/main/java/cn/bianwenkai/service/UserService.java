package cn.bianwenkai.service;

import cn.bianwenkai.entity.User;

import java.util.List;

/**
 * @author BianWenKai
 * @DATE 2022/5/14 - 2:15
 **/
public interface UserService {

    List<User> getAllUserInfo();

    int deleteUser(int userId);

}