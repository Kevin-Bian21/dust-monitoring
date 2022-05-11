package cn.bianwenkai.service.Impl;

import cn.bianwenkai.entity.DustEnvironment;
import cn.bianwenkai.mapper.DustEnvDataMapper;
import cn.bianwenkai.service.DustEnvService;
import cn.utils.HistogramData;
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
    public List<Object> getDustEnvData(float dustLimit, float temperatureLimit) {
        List<DustEnvironment> list = new ArrayList<>();
        List<HistogramData> dataList = new ArrayList<>();
        List<Object> combineList = new ArrayList<>();
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
            //为直方图数据进行一个数据的映射，以符合前端要求的数据格式
            for (int i = 0; i < 4; i++) {
                HistogramData histogramData = new HistogramData();
                histogramData.setLocal(de.getMonitorLocal());
                switch (i) {
                    case 0 : histogramData.setType("粉尘浓度"); histogramData.setValue(de.getDustDensity()); break;
                    case 1 : histogramData.setType("温度"); histogramData.setValue(de.getTemperature()); break;
                    case 2 : histogramData.setType("湿度"); histogramData.setValue(de.getHumidity()); break;
                    case 3 : histogramData.setType("风速"); histogramData.setValue(de.getWindSpeed()); break;
                    default: histogramData.setType("未知"); histogramData.setValue(0f); break;
                }
                dataList.add(histogramData);
            }
            list.add(de);
        }
        combineList.add(list);
        combineList.add(dataList);

        System.out.println(dataList);
        return combineList;
    }
}
