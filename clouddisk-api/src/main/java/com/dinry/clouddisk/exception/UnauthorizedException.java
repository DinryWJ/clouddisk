package com.dinry.clouddisk.exception;

/**
 * @Author: 吴佳杰
 * @Date: 2018/11/16 15:07
 * @Description:
 */
public class UnauthorizedException extends RuntimeException {
    private static final long serialVersionUID = -4359000182451739360L;

    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException() {
        super();
    }
}