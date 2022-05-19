package cn.bianwenkai.controller;

import cn.bianwenkai.entity.User;
import cn.bianwenkai.service.UserMangerService;
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
public class UserMangerController {

    @Resource
    private UserMangerService userMangerService;

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
        List<User> users = userMangerService.getAllUserInfo(commonVo);

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
            i = userMangerService.deleteUser(id);
        }
        Map<String, Object> map = new HashMap<>();
        if (i > 0) {
            map.put("success","ok");
            map.put("message", "删除成功!");
        } else {
            map.put("success","no");
            map.put("message","删除失败!");
        }
        return JSON.toJSONString(map);
    }

    @PostMapping("/addUser")
    @ResponseBody
    public String addUser(@RequestBody User user) {
        int i = userMangerService.addUser(user);
        Map<String, Object> map = new HashMap<>();
        if (i > 0) {
            map.put("success","ok");
            map.put("message", "添加成功!");
        } else {
            map.put("success","no");
            map.put("message","添加失败!");
        }
        return JSON.toJSONString(map);
    }

    @PostMapping("/updateUser")
    @ResponseBody
    public String updateUser(@RequestBody User user) {
        int i = userMangerService.updateUser(user);
        Map<String, Object> map = new HashMap<>();
        if (i > 0) {
            map.put("success","ok");
            map.put("message", "修改成功!");
        } else {
            map.put("success","no");
            map.put("message","修改失败!");
        }
        return JSON.toJSONString(map);
    }

}
