package cn.bianwenkai.service;

import cn.bianwenkai.entity.User;

/**
 * @author BianWenKai
 * @DATE 2022/5/4 - 17:54
 **/
public interface LoginService {

    User login(String account, String password);

}

