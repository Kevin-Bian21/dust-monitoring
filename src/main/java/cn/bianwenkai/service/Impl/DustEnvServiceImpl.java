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
    public List<DustEnvironment> getDustEnvData(float dustLimit, float temperatureLimit) {
        List<DustEnvironment> list = new ArrayList<>();
        //如果粉尘浓度或者温湿度过高，则进行预警
        for (DustEnvironment de : dustEnvDataMapper.GetEnvData()) {
            String[] tag = new String[1];
            if (de.getDustDensity() > dustLimit || de.getTemperature() > temperatureLimit) {
                tag[0] = "严重";
                de.setTags(tag);
            } else{
                tag[0] = "良好";
                de.setTags(tag);
            }
            list.add(de);
        }
        return list;
    }
}
