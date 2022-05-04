package cn.bianwenkai.service.Impl;

import cn.bianwenkai.entity.User;
import cn.bianwenkai.mapper.LoginMapper;
import cn.bianwenkai.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author BianWenKai
 * @DATE 2022/5/4 - 17:57
 **/

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private LoginMapper loginMapper;

    @Override
    public User login(String account, String password) {
        User user = loginMapper.LoginByAccount(account);
        System.out.println(user.toString());
        if (user.getPassWord().equals(password)) {
            return user;
        }
        return null;
    }
}
