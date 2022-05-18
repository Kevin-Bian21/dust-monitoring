package cn.bianwenkai.controller;

import cn.bianwenkai.service.UserService;
import cn.bianwenkai.utils.ParserJwt;
import com.alibaba.fastjson2.JSON;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author BianWenKai
 * @DATE 2022/5/18 - 23:32
 **/

@RestController
@RequestMapping(value = "/api" ,produces = {"application/json;charset=UTF-8"})
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 更新密码时首先需要验证输入的原密码和token中解析的密码一致，一致则去更新
     * @param password 前端传来的原密码和要更改的密码
     * @param token 用户登录之后为其颁发，里面包含了用户的密码
     * @return info
     */
    @PostMapping("/updatePassword")
    @ResponseBody
    public String updatePassword(@RequestBody Map<String,String> password, @RequestHeader("Authorization") String token) {
        Map<String, Object> map = new HashMap<>();
        String oldPassword = password.get("oldPassword");
        String newPassword = password.get("newPassword");
        String confirm = password.get("confirm");

        //如果新密码和再次输入的密码不一致，直接退出
        if (newPassword.equals(confirm)) {
            map.put("success",false);
            map.put("message","两次输入的密码不一致");
            return JSON.toJSONString(map);
        }
        Claims claims = ParserJwt.decoding(token);
        int userId = Integer.parseInt(claims.getId());
        if (claims.get("password").equals(oldPassword)) {
            if (userService.updatePassword(userId,newPassword) > 0) {
                map.put("success",true);
                map.put("message","修改成功,请重新登录");
            } else {
                map.put("success",false);
                map.put("message","修改的密码不能和原密码相同");
            }
        } else {
            map.put("success",false);
            map.put("message","原密码有误");
        }
        return JSON.toJSONString(map);
    }

}
