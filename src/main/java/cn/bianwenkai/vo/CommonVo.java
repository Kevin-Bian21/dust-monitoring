package cn.bianwenkai.vo;

import lombok.Data;

/**
 * 通用的实体类，实现信息的查询
 * @author BianWenKai
 * @DATE 2022/5/15 - 13:33
 **/

@Data
public class CommonVo {

    private Integer page;
    private Integer limit;
    private String searchMessage;
    private String startDateTime;
    private String endDateTime;
    private int id;

}
