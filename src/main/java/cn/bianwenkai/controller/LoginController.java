package cn.bianwenkai.controller;

import cn.bianwenkai.entity.User;
import cn.bianwenkai.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author BianWenKai
 * @DATE 2022/5/4 - 18:07
 **/
@RestController
@RequestMapping("/api")
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("/Login")
    public boolean login(String account, String password) {
        User user = loginService.login(account, password);
        return user != null;
    }

}
