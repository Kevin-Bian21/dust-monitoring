package cn.bianwenkai.service.Impl;

import cn.bianwenkai.entity.DustEnvironment;
import cn.bianwenkai.mapper.DustEnvDataMapper;
import cn.bianwenkai.service.DustEnvService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author BianWenKai
 * @DATE 2022/5/9 - 18:22
 **/

@Service
public class DustEnvServiceImpl implements DustEnvService {

    @Resource
    private DustEnvDataMapper dustEnvDataMapper;

    @Override
    public List<DustEnvironment> getDustEnvData() {
        List<DustEnvironment> list = new ArrayList<>();
        //如果粉尘浓度或者温湿度过高，则进行预警
        for (DustEnvironment de : dustEnvDataMapper.GetEnvData()) {
            String[] tag = new String[1];
            if (de.getDustDensity() > 5 && de.getHumidity() > 60 && de.getTemperature() > 10) {
                tag[0] = "良好";
                de.setTag(tag);
            } else if (de.getDustDensity() > 25 && de.getHumidity() > 40 && de.getTemperature() > 20){
                tag[0] = "中等";
                de.setTag(tag);
            } else if (de.getDustDensity() > 50 && de.getHumidity() > 0 && de.getTemperature() > 30){
                tag[0] = "严重";
                de.setTag(tag);
            }
            list.add(de);
        }
        return list;
    }
}
