package cn.bianwenkai.utils;

import cn.bianwenkai.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author BianWenKai
 * @DATE 2022/5/7 - 11:20
 **/

@Data
@AllArgsConstructor
public class AntData {
    private Boolean success;
    private String message;
    private User data;
    private String status;
    private String type;
    private String currentAuthority;
    private String access;

}
