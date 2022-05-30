package cn.bianwenkai.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author BianWenKai
 * @DATE 2022/5/20 - 10:33
 **/
@Data
public class MonitorDataVo {

    private Integer Id;
    private Float dustDensity;
    private String monitorLocal;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date monitorDateTime;
    private Float temperature;
    private Float humidity;
    private Float windSpeed;
    private String level;
    private Float dustLimit;
    private Float temperatureLimit;

}
