package cn.bianwenkai.mapper;

import cn.bianwenkai.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author BianWenKai
 * @DATE 2022/5/18 - 23:27
 **/

@Mapper
public interface UserMapper {

    int UpdatePassword(int userId, String newPassword);

    User GetPersonalDetails(int id);

}
