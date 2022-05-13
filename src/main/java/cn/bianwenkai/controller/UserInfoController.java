package cn.bianwenkai.controller;

import cn.bianwenkai.service.UserInfoService;
import com.alibaba.fastjson2.JSON;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author BianWenKai
 * @DATE 2022/5/14 - 2:12
 **/

@RestController
@RequestMapping("/api")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @GetMapping("/allUserInfo")
    public String getAllUserInfo() {
        Map<String, Object> map = new HashMap<>();
        map.put("data",userInfoService.getAllUserInfo());
        return JSON.toJSONString(map);
    }

}
