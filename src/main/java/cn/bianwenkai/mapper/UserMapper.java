package cn.bianwenkai.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author BianWenKai
 * @DATE 2022/5/18 - 23:27
 **/

@Mapper
public interface UserMapper {

    int UpdatePassword(int userId, String newPassword);

}
