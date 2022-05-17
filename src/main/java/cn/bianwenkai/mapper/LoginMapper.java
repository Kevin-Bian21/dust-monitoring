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

    //根据用户Id查找用户
    User FindUserById(int id);

    //用户登录后，更新登录信息
    int UpdateLoginInfo(int id);

}
