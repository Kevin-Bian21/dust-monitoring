package cn.bianwenkai.service;

import cn.bianwenkai.entity.User;

/**
 * @author BianWenKai
 * @DATE 2022/5/18 - 23:28
 **/
public interface UserService {

    int updatePassword(int userId, String newPassword);

    User getPersonalDetails(int id);

    int updatePersonalInfo(User user);

}
