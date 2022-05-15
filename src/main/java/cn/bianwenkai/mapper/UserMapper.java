package cn.bianwenkai.mapper;

import cn.bianwenkai.entity.User;
import cn.bianwenkai.vo.CommonVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author BianWenKai
 * @DATE 2022/5/14 - 2:14
 **/

@Mapper
public interface UserMapper {

    List<User> AllUserInfo(CommonVo commonVo);

    int DeleteUser(int userId);

    int AddUser(User user);

}
