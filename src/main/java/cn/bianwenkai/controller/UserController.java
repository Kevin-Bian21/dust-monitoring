package cn.bianwenkai.controller;

import cn.bianwenkai.entity.User;
import cn.bianwenkai.service.UserService;
import cn.bianwenkai.utils.ParserJwt;
import cn.bianwenkai.vo.CommonVo;
import com.alibaba.fastjson2.JSON;
import com.github.pagehelper.PageHelper;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BianWenKai
 * @DATE 2022/5/14 - 2:12
 **/

@RestController
@RequestMapping(value = "/api" ,produces = {"application/json;charset=UTF-8"})
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 返回满足查询条件的用户信息，如果查询条件为空，则返回所有用户
     * @param commonVo
     * @return
     */
    @PostMapping("/allUserInfo")
    @ResponseBody
    public String getAllUserInfo(@RequestBody CommonVo commonVo ,@RequestHeader("Authorization") String token) {

        PageHelper.startPage(commonVo.getPage(), commonVo.getLimit());
        Claims claims = ParserJwt.decoding(token);
        String id = claims.getId() == null ? "3" : claims.getId();
        int userId = Integer.parseInt(id);
        commonVo.setId(userId);
        List<User> users = userService.getAllUserInfo(commonVo);

        return JSON.toJSONString(users);
    }

    /**
     * 删除用户
     * @param info
     * @return
     */
    @PostMapping("/deleteUser")
    @ResponseBody
    public String deleteUser(@RequestBody Map<String, List<Integer>> info) {
        int i = 0;

        for (int id : info.get("ids")) {
            i = userService.deleteUser(id);
        }
        Map<String, Object> map = new HashMap<>();
        if (i > 0) {
            map.put("success",true);
            map.put("message", "删除成功!");
        } else {
            map.put("success",false);
            map.put("message","删除失败!");
        }
        return JSON.toJSONString(map);
    }

    @PostMapping("/addUser")
    @ResponseBody
    public String addUser(@RequestBody User user) {
        int i = userService.addUser(user);
        Map<String, Object> map = new HashMap<>();
        if (i > 0) {
            map.put("success",true);
            map.put("message", "添加成功!");
        } else {
            map.put("success",false);
            map.put("message","添加失败!");
        }
        return JSON.toJSONString(map);
    }

    @PostMapping("/updateUser")
    @ResponseBody
    public String updateUser(@RequestBody User user) {
        int i = userService.updateUser(user);
        Map<String, Object> map = new HashMap<>();
        if (i > 0) {
            map.put("success",true);
            map.put("message", "修改成功!");
        } else {
            map.put("success",false);
            map.put("message","修改失败!");
        }
        return JSON.toJSONString(map);
    }

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
            userService.updatePassword(userId,newPassword);
            map.put("success",true);
            map.put("message","修改成功,请重新登录");
        } else {
            map.put("success",false);
            map.put("message","原密码有误");
        }
        return JSON.toJSONString(map);
    }
}
