package cn.bianwenkai.service;

import cn.bianwenkai.entity.DustEnvironment;
import cn.bianwenkai.vo.CommonVo;

import java.util.List;

/**
 * @author BianWenKai
 * @DATE 2022/5/9 - 18:21
 **/
public interface DustEnvService {

    List<Object> getDustEnvData(String dustLimit, String  temperatureLimit);

    String getSingleMonitorLocalData(String local);

    String getMonitorVideo(int local);

    List<DustEnvironment> getAllMonitorData(CommonVo commonVo);

}
