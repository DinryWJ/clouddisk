package com.dinry.clouddisk.service;

import com.dinry.clouddisk.model.User;

/**
 * @Author: 吴佳杰
 * @Date: 2018/11/16 14:44
 * @Description:
 */
public interface UserService {
    /**
     * 通过账号获取用户
     * @param username
     * @return
     */
    User getUser(String username);

    /**
     * 判断用户是否存在
     * @param username
     * @return
     */
    boolean isExist(String username);

    /**
     * 判断用户是否被封禁
     * @param username
     * @return
     */
    boolean validBan(String username);

    /**
     * 判断能否登陆
     * @param username
     * @param password
     * @return
     */
    User validLogin(String username, String password);

    /**
     * 新增用户
     * @param username
     * @param password
     * @return
     */
    int saveUser(String username, String password);
}
