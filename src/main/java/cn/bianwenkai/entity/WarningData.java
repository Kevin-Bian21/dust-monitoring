package cn.bianwenkai.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author BianWenKai
 * @DATE 2022/5/14 - 0:45
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarningData {

    private Integer Id;
    private Float dustLimit;
    private Float temperatureLimit;
    private String level;

}
