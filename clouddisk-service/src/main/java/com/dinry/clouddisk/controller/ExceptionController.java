package com.dinry.clouddisk.controller;

import com.dinry.clouddisk.ApiResponse;
import com.dinry.clouddisk.exception.UnauthorizedException;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 吴佳杰
 * @Date: 2018/11/16 15:09
 * @Description:
 */
@RestControllerAdvice
public class ExceptionController {

    /**
     * 捕捉 Shiro 异常
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public ResponseEntity<ApiResponse> handle401(ShiroException e) {
        return ApiResponse.response(HttpStatus.UNAUTHORIZED.value(), e.toString(), null);
    }

    /**
     *  捕捉UnauthorizedException
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse> handle401() {
        return ApiResponse.response(HttpStatus.UNAUTHORIZED.value(), "Unauthorized", null);
    }

    /**
     * 捕捉其他所有异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse> globalException(HttpServletRequest request, Throwable ex) {
        return ApiResponse.response(getStatus(request).value(), ex.getMessage(), null);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}