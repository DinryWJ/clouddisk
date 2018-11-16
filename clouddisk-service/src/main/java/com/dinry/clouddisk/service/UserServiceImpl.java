package com.dinry.clouddisk.service;

import com.dinry.clouddisk.dao.UserMapper;
import com.dinry.clouddisk.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 吴佳杰
 * @Date: 2018/11/16 14:45
 * @Description:
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User getUser(String username) {
        User user = new User();
        user.setUsername(username);
        return userMapper.selectOne(user);
    }

}
