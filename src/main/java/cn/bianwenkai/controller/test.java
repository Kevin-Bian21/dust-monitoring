package cn.bianwenkai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author BianWenKai
 * @DATE 2022/4/30 - 12:30
 **/

@Controller
//@RequestMapping("/hello")
public class test {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello World!" ;
    }


}
