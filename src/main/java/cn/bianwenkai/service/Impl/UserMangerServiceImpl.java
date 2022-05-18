package cn.bianwenkai.service.Impl;

import cn.bianwenkai.entity.User;
import cn.bianwenkai.mapper.UserMangerMapper;
import cn.bianwenkai.service.UserMangerService;
import cn.bianwenkai.vo.CommonVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author BianWenKai
 * @DATE 2022/5/14 - 2:16
 **/

@Service
public class UserMangerServiceImpl implements UserMangerService {

    @Resource
    private UserMangerMapper userMangerMapper;

    @Override
    public List<User> getAllUserInfo(CommonVo commonVo) {
        return userMangerMapper.AllUserInfo(commonVo);
    }

    @Override
    @Transactional
    public int deleteUser(int id) {
        return  userMangerMapper.DeleteUser(id);
    }

    @Override
    public int addUser(User user) {
        return userMangerMapper.AddUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userMangerMapper.UpdateUser(user);
    }

}
