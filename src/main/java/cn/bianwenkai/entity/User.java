package cn.bianwenkai.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author BianWenKai
 * @DATE 2022/5/4 - 17:16
 **/

@Data
public class User {

    private Integer userId;
    private String userName;
    private String loginAccount;
    private String passWord;
    private String phone;
    private String email;
    @DateTimeFormat
    private Date generateTime;
    @DateTimeFormat
    private Date lastLoginTime;
    private Integer loginCount;

}
