package cn.bianwenkai.service.Impl;

import cn.bianwenkai.entity.DustEnvironment;
import cn.bianwenkai.entity.WarningData;
import cn.bianwenkai.mapper.DustEnvDataMapper;
import cn.bianwenkai.service.DustEnvService;
import cn.bianwenkai.utils.BeanProvider;
import cn.bianwenkai.utils.HistogramData;
import cn.bianwenkai.utils.MailService;
import cn.bianwenkai.vo.CommonVo;
import cn.bianwenkai.vo.MonitorDataVo;
import cn.bianwenkai.vo.SearchData;
import com.alibaba.fastjson2.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BianWenKai
 * @DATE 2022/5/9 - 18:22
 **/

@Service
public class DustEnvServiceImpl implements DustEnvService {

    private static SearchData searchData = new SearchData(1, 10);

    private static final String COMPANY_EMAIL = "1467295546@qq.com";
    private static final String TITLE = "粉尘浓度预警通知！";

    @Resource
    private DustEnvDataMapper dustEnvDataMapper;

    @Autowired
    private MailService mailService;

    //用来记录同一个监测点连续出现预警的次数，从而判断是否要发送邮件通知。
    private Map<String, Integer> map = new HashMap<>();

    Boolean initMapFlag = true;

    /**
     * 通过工具类来获取DustEnvMapper实例对象，线程中不允许通过注解来注入对象
     * @param dust
     * @param temperature
     * @return
     */
    @Override
    public List<Object> getDustEnvData(String dust, String temperature) {

        float dustLimit = (dust == null) ? 50f : Float.parseFloat(dust);
        float temperatureLimit = (temperature == null) ? 30 : Float.parseFloat(temperature);


        List<DustEnvironment> list = new ArrayList<>();
        List<HistogramData> dataList = new ArrayList<>();
        List<Object> combineList = new ArrayList<>();
        //如果粉尘浓度或者温湿度过高，则进行预警

        DustEnvDataMapper bean = (DustEnvDataMapper)BeanProvider.getBean(DustEnvDataMapper.class);

        String[] allUserEmail = dustEnvDataMapper.GetAllUserEmail();
        for (DustEnvironment de : bean.GetEnvData(searchData.getStart(), searchData.getEnd())) {
            //对记录某个监测点连续出现的map对象初始化
            if (initMapFlag) {
                map.put(de.getMonitorLocal(), 0);
                initMapFlag = false;
            }
            if (de.getDustDensity() > dustLimit && de.getTemperature() > temperatureLimit) {
                de.setTag("严重");

                //下一次该检测点预警等级为严重，则让其value值加一
                map.put(de.getMonitorLocal(),map.get(de.getMonitorLocal()) + 1);

                //如果某个监测点连续预警次数大于等于10次，则发送电子邮件通知管理员进行处理
                if (map.get(de.getMonitorLocal()) >= 10) {
                    sendEmail(dustLimit, temperatureLimit, de, allUserEmail);
                }

                //如果超过预警值则将其记录
                earlyWarningRecord(de, dustLimit, temperatureLimit, bean,"严重");
            } else{
                de.setTag("良好");
                //下一次该检测点预警等级不为严重，则将其连续预警值清空。
                map.put(de.getMonitorLocal(),0);
                earlyWarningRecord(de, dustLimit, temperatureLimit, bean,"良好");
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
        //为了模拟硬件每20秒传一次数据，防止数据溢出
        if (searchData.getEnd()>Integer.MAX_VALUE - 100) {
            searchData.setStart(1);
            searchData.setEnd(10);
        }
        searchData.setStart(searchData.getEnd()+1);
        searchData.setEnd(searchData.getEnd() +10);
        System.out.println(searchData.getEnd());
        combineList.add(list);
        combineList.add(dataList);

        System.out.println(dataList);
        return combineList;
    }


    /**
     * 获取具体监测位置的数据
     * @param local
     * @return
     */

    @Override
    public String getSingleMonitorLocalData(String local) {
        Map<String, Object> map = new HashMap<>();
        List<Float> dustList = new ArrayList<>();
        List<Float> temperatureList = new ArrayList<>();
        List<Float> humidityList = new ArrayList<>();
        List<Float> windSpeedList = new ArrayList<>();
        List<Object> monitorDataTimeList = new ArrayList<>();
        for (DustEnvironment de : dustEnvDataMapper.GetSingleMonitorLocalData(local)) {
            dustList.add(de.getDustDensity());
            temperatureList.add(de.getTemperature());
            humidityList.add(de.getHumidity());
            windSpeedList.add(de.getWindSpeed());
            monitorDataTimeList.add(de.getMonitorDateTime());
        }
        map.put("dustDensity", dustList);
        map.put("temperature", temperatureList);
        map.put("humidity", humidityList);
        map.put("windSpeed", windSpeedList);
        map.put("monitorTime", monitorDataTimeList);
        return JSON.toJSONString(map);
    }

    @Override
    public String getMonitorVideo(int local) {
        String src = null;
        switch (local) {
            case 1 : src = "https://bianwenkai.oss-cn-beijing.aliyuncs.com/video/local_1.mp4"; break;
            case 2 : src = "https://bianwenkai.oss-cn-beijing.aliyuncs.com/video/local-2.mp4"; break;
            case 3 : src = "https://bianwenkai.oss-cn-beijing.aliyuncs.com/video/local-3.mp4"; break;
            case 4 : src = "https://bianwenkai.oss-cn-beijing.aliyuncs.com/video/local-4.mp4"; break;
            case 5 : src = "https://bianwenkai.oss-cn-beijing.aliyuncs.com/video/local-5.mp4"; break;
            case 6 : src = "https://bianwenkai.oss-cn-beijing.aliyuncs.com/video/local_1.mp4"; break;
            default:  src = "https://bianwenkai.oss-cn-beijing.aliyuncs.com/video/local-2.mp4"; break;
        }
        return src;
    }

    @Override
    public List<MonitorDataVo> getAllMonitorData(CommonVo commonVo) {
        return dustEnvDataMapper.AllMonitorData(commonVo);
    }

    @Override
    public int NumberOfEarlyWarningRecord() {
        return dustEnvDataMapper.NumberOfEarlyWarningRecord();
    }


    private void earlyWarningRecord(DustEnvironment de, float dustLimit, float temperatureLimit, DustEnvDataMapper bean , String level) {
        WarningData warningData = new WarningData(de.getId(),dustLimit,temperatureLimit,level);
        bean.EarlyWarningRecord(warningData);
    }

    private void sendEmail(float dustLimit, float temperatureLimit, DustEnvironment de, String[] allUserEmail) {
        //向用户通过电子邮件发送超出预警值的数据
        String text = "监测时间:" + de.getMonitorDateTime() + "\n\n" + "当前预警值： " + "粉尘浓度：" + dustLimit + "g/m³" + "温度：" + temperatureLimit + "℃ 。"
                + "\n" + "预警位置："+de.getMonitorLocal() + "\n" + "监测数据：  "+ "粉尘浓度：\t" +de.getDustDensity()+"温度：\t" + de.getTemperature()
                + "湿度：\t" +de.getHumidity() + "风速:\t"+de.getWindSpeed();
        mailService.sendMail(COMPANY_EMAIL, allUserEmail, TITLE, text);
    }
}
