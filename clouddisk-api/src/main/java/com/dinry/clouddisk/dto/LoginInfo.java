package com.dinry.clouddisk.dto;

/**
 * @Author: 吴佳杰
 * @Date: 2018/11/21 11:55
 * @Description:
 */
public class LoginInfo {
    private String username;
    private Integer userId;

    public LoginInfo(String username, Integer userId) {
        this.username = username;
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
