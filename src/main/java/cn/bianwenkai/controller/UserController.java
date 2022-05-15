package cn.bianwenkai.controller;

import cn.bianwenkai.service.UserService;
import com.alibaba.fastjson2.JSON;
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

    @GetMapping("/allUserInfo")
    @ResponseBody
    public String getAllUserInfo() {
        Map<String, Object> map = new HashMap<>();
        map.put("data",userService.getAllUserInfo());
        return JSON.toJSONString(map);
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
            map.put("message","删除失败");
        }
        return JSON.toJSONString(map);
    }
}
