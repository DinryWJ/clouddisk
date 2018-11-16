package com.dinry.clouddisk.service;

import com.dinry.clouddisk.model.User;

/**
 * @Author: 吴佳杰
 * @Date: 2018/11/16 14:44
 * @Description:
 */
public interface UserService {
    User getUser(String username);
}
