package com.dinry.clouddisk.common;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Author: 吴佳杰
 * @Date: 2018/11/16 15:27
 * @Description:
 */
public class JwtToken implements AuthenticationToken {
    private static final long serialVersionUID = -6101204493587610246L;
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
