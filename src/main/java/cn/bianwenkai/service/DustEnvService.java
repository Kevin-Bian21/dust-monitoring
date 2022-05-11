package cn.bianwenkai.service;

import java.util.List;

/**
 * @author BianWenKai
 * @DATE 2022/5/9 - 18:21
 **/
public interface DustEnvService {

    List<Object> getDustEnvData(float dustLimit, float temperatureLimit);

}
