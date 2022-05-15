package cn.bianwenkai.controller;

import cn.bianwenkai.entity.User;
import cn.bianwenkai.service.LoginService;
import cn.bianwenkai.utils.CreateJwt;
import cn.bianwenkai.utils.ParserJwt;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author BianWenKai
 * @DATE 2022/5/4 - 18:07
 **/
@RestController
@RequestMapping(value = "/api" ,produces = {"application/json;charset=UTF-8"})
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody Map<String, String> info) throws JsonProcessingException {
        User user = loginService.login(info.get("username"), info.get("password"));
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> userMap = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        if (user != null) {
            map.put("success",true);
            map.put("message","");
            userMap.put("id",user.getUserId());
            userMap.put("username",user.getUserName());
            map.put("data", userMap );
            String token = CreateJwt.getoken(user);
            map.put("token",token);
        } else {
            map.put("success",false);
            map.put("message","Incorrect username or password.");
            map.put("data", userMap );
        }
        return mapper.writeValueAsString(map);
    }

    @GetMapping("/currentUser")
    @ApiOperation("获取当前登录的用户信息")
    @ResponseBody
    public String getCurrentUser(@RequestHeader("Authorization") String token) throws JsonProcessingException {
        System.out.println(token);
        Map<String,Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        if (token == null) {
            map.put("currentUser",null);
        } else {
            Claims claims = ParserJwt.decoding(token);
            int userId = Integer.parseInt(claims.getId());
            String userName = claims.getSubject();
            User user = loginService.findUserById(userId);
            if (claims.getId() != null && userName != null && userName.equals(user.getUserName())) {
                return mapper.writeValueAsString(user);
            }
        }
        return mapper.writeValueAsString(map);
    }


    @GetMapping("/logout")
    @ResponseBody
    public String logout(@RequestHeader("Authorization") String token) throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        map.put("success", true);
        map.put("message", "退出登录成功");
        map.put("data", new Object[0]);
        return mapper.writeValueAsString(map);
    }
}
