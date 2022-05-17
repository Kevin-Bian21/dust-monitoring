package cn.bianwenkai.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author BianWenKai
 * @DATE 2022/5/4 - 17:17
 **/

@Data
public class DustEnvironment {

    private Integer id;
    private Float dustDensity;
    private String monitorLocal;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date monitorDateTime;
    private Float temperature;
    private Float humidity;
    private Float windSpeed;
    private String tag;

}
