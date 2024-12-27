package com.washy.springtest.controller;

import com.washy.springtest.pojo.dto.role.RoleDto;
import com.washy.springtest.pojo.dto.role.RoleMenuRelationDto;
import com.washy.springtest.pojo.dto.role.RolePageDto;
import com.washy.springtest.pojo.dto.user.UserPageDto;
import com.washy.springtest.service.RoleService;
import com.washy.springtest.utils.Result;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: washy
 * @Date: 2024/07/08/16:27
 */
@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/role")
public class RoleController {

    private final RoleService roleService;

    @ApiOperation("新增角色")
    @PostMapping("/create")
    public Result<?> createRole(@RequestBody RoleDto roleDto) {
        return Result.auto(roleService.createRole(roleDto));
    }

    @ApiOperation("获取角色分页")
    @GetMapping("/page")
    public Result<?> getUserPage(RolePageDto rolePageDto) {
        return Result.successOf(roleService.getRolePage(rolePageDto));
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/delete")
    public Result<?> deleteRole(@RequestBody Long roleId) {
        return Result.auto(roleService.deleteRole(roleId));
    }

    @ApiOperation("创建角色菜单关联")
    @PostMapping("/menu_relation")
    public Result<?> createRoleMenuRelation(@RequestBody RoleMenuRelationDto roleMenuRelationDto) {
        return Result.auto(roleService.createRoleMenuRelation(roleMenuRelationDto));
    }
}
