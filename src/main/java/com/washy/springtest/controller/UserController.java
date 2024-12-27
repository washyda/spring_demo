package com.washy.springtest.controller;

import com.washy.springtest.pojo.dto.user.UserDto;
import com.washy.springtest.pojo.dto.user.UserPageDto;
import com.washy.springtest.pojo.dto.user.UserRoleRelationDto;
import com.washy.springtest.service.UserService;
import com.washy.springtest.utils.Result;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created with IntelliJ IDEA.
 * @Author: washy
 * @Date: 2024/04/24/14:18
 */
@RestController
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    @ApiOperation("新增用户")
    @PostMapping("/create")
    public Result<?> createUser(@RequestBody @Valid UserDto userDto) {
        return Result.auto(userService.createUser(userDto));
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public Result<?> login(@RequestBody @Valid UserDto userDto) {
        return Result.successOf(userService.login(userDto));
    }

    @ApiOperation("获取用户分页")
    @GetMapping("/page")
    public Result<?> getUserPage(UserPageDto userPageDto) {
        return Result.successOf(userService.getUserPage(userPageDto));
    }

    @ApiOperation("编辑用户")
    @PostMapping("/edit")
    public Result<?> editUser(@RequestBody @Valid UserDto userDto) {
        return Result.auto(userService.editUser(userDto));
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/delete")
    public Result<?> deleteUser(@RequestBody Long userId) {
        return Result.auto(userService.deleteUser(userId));
    }

    @ApiOperation("建立用户角色关联")
    @PostMapping("/role_relation")
    public Result<?> userRelation(@RequestBody UserRoleRelationDto userRoleRelationDto) {
        return Result.auto(userService.userRelation(userRoleRelationDto));
    }

    @ApiOperation("删除用户角色关联")
    @DeleteMapping("/role_relation")
    public Result<?> deleteUserRelation(@RequestBody UserRoleRelationDto userRoleRelationDto) {
        return Result.auto(userService.userRelation(userRoleRelationDto));
    }
}
