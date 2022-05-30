package cn.bianwenkai.service;

import cn.bianwenkai.vo.CommonVo;
import cn.bianwenkai.vo.MonitorDataVo;

import java.util.List;

/**
 * @author BianWenKai
 * @DATE 2022/5/9 - 18:21
 **/
public interface DustEnvService {

    List<Object> getDustEnvData(String dustLimit, String  temperatureLimit);

    String getSingleMonitorLocalData(String local);

    String getMonitorVideo(int local);

    List<MonitorDataVo> getAllMonitorData(CommonVo commonVo);

    int NumberOfEarlyWarningRecord();

}
