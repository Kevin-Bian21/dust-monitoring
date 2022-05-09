package cn.bianwenkai.controller;

import cn.bianwenkai.service.DustEnvService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author BianWenKai
 * @DATE 2022/5/9 - 18:10
 **/

@RestController
@RequestMapping(value = "/api" ,produces = {"application/json;charset=UTF-8"})
public class DustEnvDataController {

    @Resource
    private DustEnvService dustEnvService;

    @GetMapping("/presentEnvData")
    @ResponseBody
    public String getPresentEnvData() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();
        map.put("data",dustEnvService.getDustEnvData() );
        return mapper.writeValueAsString(map);
    }

}
