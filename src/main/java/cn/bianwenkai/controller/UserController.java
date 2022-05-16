package cn.bianwenkai.controller;

import cn.bianwenkai.entity.User;
import cn.bianwenkai.service.UserService;
import cn.bianwenkai.vo.CommonVo;
import com.alibaba.fastjson2.JSON;
import com.github.pagehelper.PageHelper;
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
    public String getAllUserInfo(@RequestBody CommonVo commonVo) {

        PageHelper.startPage(commonVo.getPage(), commonVo.getLimit());
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
}
