package cn.bianwenkai.service;

import cn.bianwenkai.entity.User;
import cn.bianwenkai.vo.CommonVo;

import java.util.List;

/**
 * @author BianWenKai
 * @DATE 2022/5/14 - 2:15
 **/
public interface UserMangerService {

    //只能管理比自己权限小的用户信息
    List<User> getAllUserInfo(CommonVo commonVo);

    int deleteUser(int userId);

    int addUser(User user);

    int updateUser(User user);

}
