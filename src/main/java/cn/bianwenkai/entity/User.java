package cn.bianwenkai.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

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
    private String avatar;
    private String phone;
    private String email;
    @JsonFormat
    private Date generateTime;
    @JsonFormat
    private Date lastLoginTime;
    private Integer loginCount;
    private String access;

}
