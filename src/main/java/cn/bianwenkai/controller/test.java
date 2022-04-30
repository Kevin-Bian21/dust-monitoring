package cn.bianwenkai.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BianWenKai
 * @DATE 2022/4/30 - 12:30
 **/

@RestController
@RequestMapping("/hello")
public class test {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello World!" ;
    }


}
