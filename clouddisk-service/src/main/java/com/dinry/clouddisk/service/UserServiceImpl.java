package com.dinry.clouddisk.service;

import com.dinry.clouddisk.common.RandomString;
import com.dinry.clouddisk.dao.UserMapper;
import com.dinry.clouddisk.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.Objects;
import java.util.Random;

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

    @Override
    public boolean isExist(String username) {
        User user = new User();
        user.setUsername(username);
        return userMapper.selectCount(user) > 0;
    }

    @Override
    public boolean validBan(String username) {
        User user = new User();
        user.setUsername(username);
        user.setStatus(true);
        return userMapper.selectCount(user) > 0;
    }

    @Override
    public User validLogin(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user = userMapper.selectOne(user);
        if (Objects.equals(user.getPassword(),DigestUtils.md5DigestAsHex((DigestUtils.md5DigestAsHex(password.getBytes())+user.getSalt()).getBytes()))){
            return user;
        }
        return null;
    }

    @Override
    public int saveUser(String username, String password) {
        String salt = RandomString.generateString(new Random(), 6);
        User user = new User();
        user.setUsername(username);
        user.setPassword(DigestUtils.md5DigestAsHex((DigestUtils.md5DigestAsHex(password.getBytes())+salt).getBytes()));
        user.setSalt(salt);
        user.setNickname("unknown");
        user.setPermission("edit,view");
        user.setRole("user");
        user.setRank(0);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setStatus(false);
        return userMapper.insertSelective(user);
    }

}
