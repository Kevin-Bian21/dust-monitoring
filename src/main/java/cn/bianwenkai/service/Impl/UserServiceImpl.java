package cn.bianwenkai.service.Impl;

import cn.bianwenkai.entity.User;
import cn.bianwenkai.mapper.UserMapper;
import cn.bianwenkai.service.UserService;
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
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> getAllUserInfo(CommonVo commonVo) {
        return userMapper.AllUserInfo(commonVo);
    }

    @Override
    @Transactional
    public int deleteUser(int id) {
        return  userMapper.DeleteUser(id);
    }
}
