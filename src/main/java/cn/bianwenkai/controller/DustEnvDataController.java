package cn.bianwenkai.controller;

import cn.bianwenkai.entity.DustEnvironment;
import cn.bianwenkai.service.DustEnvService;
import cn.bianwenkai.vo.CommonVo;
import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
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


    @GetMapping("/getDataThroughMonitorLocal")
    @ResponseBody
    public String getSingleMonitorLocalData(@RequestParam("local") String local) {
        return dustEnvService.getSingleMonitorLocalData(local) ;
    }

    @GetMapping("/getMonitorVideoSrc")
    @ResponseBody
    public String getMonitorVideo(@RequestParam("local") Integer local) {
        return dustEnvService.getMonitorVideo(local);
    }

    @PostMapping("/getAllMonitorData")
    @ResponseBody
    public String getAllMonitorData(@RequestBody CommonVo commonVo){
        PageHelper.startPage(commonVo.getPage(),commonVo.getLimit());
        List<DustEnvironment> envData =  dustEnvService.getAllMonitorData(commonVo);
        return JSON.toJSONString(envData);
    }

}
