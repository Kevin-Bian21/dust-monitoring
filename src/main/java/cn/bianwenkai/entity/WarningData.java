package cn.bianwenkai.entity;

import lombok.Data;

/**
 * @author BianWenKai
 * @DATE 2022/5/14 - 0:45
 **/

@Data
public class WarningData {

    private Float dustLimit;
    private Float temperatureLimit;
    private Float humidityLimit;
    private Float windSpeedLimit;

}
