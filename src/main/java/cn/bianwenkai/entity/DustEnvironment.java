package cn.bianwenkai.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author BianWenKai
 * @DATE 2022/5/4 - 17:17
 **/

@Data
public class DustEnvironment {

    Integer id;
    Float dustDensity;
    String monitorLocal;
    Date monitorDateTime;
    Float temperature;
    Float humidity;
    Float windSpeed;

}
