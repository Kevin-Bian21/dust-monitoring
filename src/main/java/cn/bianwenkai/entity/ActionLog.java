package cn.bianwenkai.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author BianWenKai
 * @DATE 2022/5/4 - 17:17
 **/

@Data
public class ActionLog {
    private Integer id;
    private Float dustDensity;
    private String monitorLocal;
    private Date monitorDateTime;
    private Float temperature;
    private Float humidity;
    private Float windSpeed;
}
