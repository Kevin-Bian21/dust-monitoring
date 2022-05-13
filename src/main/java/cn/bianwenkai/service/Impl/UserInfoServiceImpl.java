package cn.bianwenkai.service.Impl;

import cn.bianwenkai.entity.User;
import cn.bianwenkai.mapper.UserInfoMapper;
import cn.bianwenkai.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author BianWenKai
 * @DATE 2022/5/14 - 2:16
 **/

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public List<User> getAllUserInfo() {
        return userInfoMapper.AllUserInfo();
    }
}
