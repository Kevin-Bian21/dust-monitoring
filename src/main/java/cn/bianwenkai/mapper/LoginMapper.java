package cn.bianwenkai.mapper;

import cn.bianwenkai.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author BianWenKai
 * @DATE 2022/5/4 - 17:31
 **/

@Mapper
public interface LoginMapper {

    User LoginByAccount(String account);

    //通过用户ID来确认用户身份
    String UserIdentity(int id);

    //根据用户Id查找用户
    User FindUserById(int id);

}
