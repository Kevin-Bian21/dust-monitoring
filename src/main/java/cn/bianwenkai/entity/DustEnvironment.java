package cn.bianwenkai.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author BianWenKai
 * @DATE 2022/5/4 - 17:17
 **/

@Data
public class DustEnvironment {

    public Integer id;
    public Float dustDensity;
    public String monitorLocal;
    public Date monitorDateTime;
    public Float temperature;
    public Float humidity;
    public Float windSpeed;

}
