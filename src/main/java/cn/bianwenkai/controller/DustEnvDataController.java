package cn.bianwenkai.controller;

import cn.bianwenkai.service.DustEnvService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/presentEnvData")
    @ResponseBody
    public String getPresentEnvData(@RequestBody Map<String, String> info) throws JsonProcessingException {
        String dustLimit = info.get("dustLimit");
        String temperatureLimit = info.get("temperatureLimit");

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();
        map.put("data",dustEnvService.getDustEnvData(dustLimit,temperatureLimit));
        System.out.println(mapper.writeValueAsString(map));
        return mapper.writeValueAsString(map);
    }

}
