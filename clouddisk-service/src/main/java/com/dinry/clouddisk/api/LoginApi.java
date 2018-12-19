package com.dinry.clouddisk.api;

import com.dinry.clouddisk.ApiResponse;
import com.dinry.clouddisk.common.JwtUtil;
import com.dinry.clouddisk.model.User;
import com.dinry.clouddisk.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


/**
 * @Author: 吴佳杰
 * @Date: 2018/11/16 16:15
 * @Description:
 */
@Api(value = "登陆管理", description = "登陆管理")

@RestController
public class LoginApi {

    private final UserService userService;

    @Autowired
    public LoginApi(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "登陆")
    @PostMapping(value = "/login/login")
    public ResponseEntity<ApiResponse> login(
            @ApiParam(value = "用户账号", required = true) @RequestParam(value = "username", required = true) String username,
            @ApiParam(value = "密码", required = true) @RequestParam(value = "password", required = true) String password
    ) {
        User user = userService.validLogin(username, password);
        if (user != null) {
            if (userService.validBan(username)) {
                return ApiResponse.errorResponse("用户已被封禁");
            }
            return ApiResponse.successResponse(JwtUtil.sign(username, user.getPassword()));
        } else {
            return ApiResponse.errorResponse("用户名密码错误");
        }
    }

    @ApiOperation(value = "注册")
    @PostMapping(value = "/login/register")
    public ResponseEntity<ApiResponse> register(
            @ApiParam(value = "用户账号", required = true) @RequestParam(value = "username", required = true) String username,
            @ApiParam(value = "密码", required = true) @RequestParam(value = "pass", required = true) String pass,
            @ApiParam(value = "重复密码", required = true) @RequestParam(value = "checkpass", required = true) String checkpass

    ) {
        if (!Objects.equals(pass, checkpass)) {
            return ApiResponse.errorResponse("两次密码不一致");
        }
        if (!userService.isExist(username)) {
            int eff = userService.saveUser(username, pass);
            return ApiResponse.successResponse(eff);
        }else {
            return ApiResponse.errorResponse("用户已存在");
        }
    }

    @ApiOperation(value = "验证账号")
    @GetMapping(value = "/login/valid")
    public ResponseEntity<ApiResponse> valid(
            @ApiParam(value = "用户账号", required = true) @RequestParam(value = "username", required = true) String username
    ) {
        if (!userService.isExist(username)) {
            return ApiResponse.successResponse("");
        } else {
            return ApiResponse.errorResponse("账号已存在");
        }
    }


    @ApiOperation(value = "a")
    @PostMapping(value = "/article")
    public ResponseEntity<ApiResponse> article() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return ApiResponse.successResponse("You are already logged in");
        } else {
            return ApiResponse.successResponse("You are guest");
        }
    }

    @GetMapping("/require_auth")
    @RequiresAuthentication
    public ResponseEntity<ApiResponse> requireAuth() {
        return ApiResponse.successResponse("You are authenticated");
    }

    @PostMapping("/require_role")
    @RequiresRoles("admin")
    public ResponseEntity<ApiResponse> requireRole() {
        return ApiResponse.successResponse("You are visiting require_role");
    }

    @PostMapping("/require_permission")
    @RequiresPermissions(logical = Logical.AND, value = {"view", "edit"})
    public ResponseEntity<ApiResponse> requirePermission() {
        return ApiResponse.successResponse("You are visiting permission require edit,view");
    }

    @GetMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ApiResponse> unauthorized() {
        return ApiResponse.response(HttpStatus.UNAUTHORIZED.value(), "Unauthorized", null);
    }
}
