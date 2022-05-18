package cn.bianwenkai.service.Impl;

import cn.bianwenkai.mapper.UserMapper;
import cn.bianwenkai.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author BianWenKai
 * @DATE 2022/5/18 - 23:29
 **/
@Service
public class UserServiceImpl implements UserService {


    @Resource
    private UserMapper userMapper;

    @Override
    public int updatePassword(int userId, String newPassword) {
        return userMapper.UpdatePassword(userId, newPassword);
    }

}
