package cn.bianwenkai.service;

import cn.bianwenkai.entity.User;
import cn.bianwenkai.vo.CommonVo;

import java.util.List;

/**
 * @author BianWenKai
 * @DATE 2022/5/14 - 2:15
 **/
public interface UserService {

    List<User> getAllUserInfo(CommonVo commonVo);

    int deleteUser(int userId);

    int addUser(User user);

}
