package cn.bianwenkai.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BianWenKai
 * @DATE 2022/4/30 - 12:30
 **/

@RestController
@RequestMapping("/api")
public class test {

    @ApiOperation("test 控制类")
    @PostMapping("/hello")
    public String hello(@ApiParam("用户名") String userName) {
        return "Hello World!" ;
    }


}
