package com.dinry.clouddisk.api;

import com.dinry.clouddisk.common.JwtUtil;
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


/**
 * @Author: 吴佳杰
 * @Date: 2018/11/16 16:15
 * @Description:
 */
@Api(value = "登陆管理", description = "登陆管理")

@RestController
public class LoginApi {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "登陆")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> login(
            @ApiParam(name = "username", value = "用户账号", required = true) @RequestParam(value = "username", required = true) String username,
            @ApiParam(value = "密码", name = "password", required = true) @RequestParam(value = "password", required = true) String password
    ) {
        return ApiResponse.successResponse(JwtUtil.sign(username,password));
    }

    @ApiOperation(value = "a")
    @RequestMapping(value = "/article", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> article() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return ApiResponse.successResponse("You are already logged in");
        } else {
            return ApiResponse.successResponse("You are guest");
        }
    }

    @PostMapping("/require_auth")
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
