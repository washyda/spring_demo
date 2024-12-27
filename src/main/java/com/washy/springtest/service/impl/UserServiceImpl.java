package com.washy.springtest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.washy.springtest.entity.*;
import com.washy.springtest.enums.ErrorCodeEnum;
import com.washy.springtest.exception.GlobalException;
import com.washy.springtest.mapper.*;
import com.washy.springtest.pojo.dto.user.UserDto;
import com.washy.springtest.pojo.dto.user.UserPageDto;
import com.washy.springtest.pojo.dto.user.UserRoleRelationDto;
import com.washy.springtest.pojo.vo.user.MenuInfoVo;
import com.washy.springtest.pojo.vo.role.RoleInfoVo;
import com.washy.springtest.pojo.vo.user.UserInfoVo;
import com.washy.springtest.pojo.vo.user.UserPageVo;
import com.washy.springtest.pojo.vo.user.UserVo;
import com.washy.springtest.service.UserService;
import com.washy.springtest.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: washy
 * @Date: 2024/06/26/13:55
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Resource
    UserRoleMapper userRoleMapper;

    @Resource
    RoleMenuMapper roleMenuMapper;

    @Resource
    RoleMapper roleMapper;

    @Resource
    MenuMapper menuMapper;

    @Override
    public boolean createUser(UserDto userDto) {
        User findUser = userMapper.getUserByUsername(userDto.getUsername());
        if (findUser != null) {
            throw new GlobalException(ErrorCodeEnum.B_User_Repeat);
        }
        User user = User.builder()
                .username(userDto.getUsername())
                .password(bCryptPasswordEncoder.encode(userDto.getPassword()))
                .build();
        return userMapper.insert(user) > 0;
    }

    @Override
    public UserInfoVo login(UserDto userDto) {
        User user = userMapper.getUserByUsername(userDto.getUsername());
        // 用户不存在
        if (user == null) {
            throw new GlobalException(ErrorCodeEnum.B_Not_Found_User);
        }
        // 密码错误
        boolean isValidLogin = bCryptPasswordEncoder.matches(userDto.getPassword(), user.getPassword());
        if (!isValidLogin) {
            throw new GlobalException(ErrorCodeEnum.B_Password_Error);
        }
        // 登录成功 初始化用户信息
        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setRoleList(Collections.emptyList());
        userInfoVo.setMenuList(Collections.emptyList());
        // 拷贝用户信息 去除密码
        BeanUtils.copyProperties(user, userInfoVo, "password");
        // 生成token
        userInfoVo.setToken(JwtUtils.generateToken(user.getId(), user.getUsername()));
        // 获取用户角色
        List<UserRole> userRoles = userRoleMapper.getUserRolesByUserId(user.getId());
        // 关联角色不为空查询角色列表及菜单列表
        if (CollectionUtils.isNotEmpty(userRoles)) {
            // 查询角色列表
            List<Long> roleIds = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
            List<Role> roles = roleMapper.selectBatchIds(roleIds);
            userInfoVo.setRoleList(roles.stream().map(role -> RoleInfoVo.builder().roleId(role.getId()).roleName(role.getRoleName()).roleCode(role.getRoleCode()).build()).collect(Collectors.toList()));

            // 查询菜单列表
            List<RoleMenu> roleMenus = roleMenuMapper.getMenusByRoleIds(roleIds);
            List<Menu> menus = menuMapper.selectBatchIds(roleMenus.stream().map(RoleMenu::getMenuId).collect(Collectors.toList()));
            // 得到menus的父级id并去重
            List<Long> parentIds = menus.stream().map(Menu::getParentId).distinct().collect(Collectors.toList());
            List<Menu> parentMenus = menuMapper.selectBatchIds(parentIds);
            // 组合menus
            List<MenuInfoVo> collect = Stream.concat(menus.stream(), parentMenus.stream()).map((Menu menu) -> MenuInfoVo.builder()
                            .id(menu.getId())
                            .parentId(menu.getParentId())
                            .routeName(menu.getRouteName())
                            .path(menu.getPath())
                            .routePath(menu.getRoutePath())
                            .build())
                    .collect(Collectors.toList());
            userInfoVo.setMenuList(collect);
        }
        return userInfoVo;
    }

    @Override
    public UserPageVo getUserPage(UserPageDto userPageDto) {
        Page<User> userPage = new Page<>(userPageDto.getPageNum(), userPageDto.getPageSize());
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.orderByAsc("id");
        if(StringUtils.isNotEmpty(userPageDto.getQuerySearch())) {
            userQueryWrapper.like("username", userPageDto.getQuerySearch());
        }

        if(userPageDto.getStartTime() != null && userPageDto.getEndTime() != null) {
            userQueryWrapper.between("create_time", userPageDto.getStartTime(), userPageDto.getEndTime());
        }

        Page<User> pageResult = userMapper.selectPage(userPage, userQueryWrapper);
        pageResult.setTotal(userMapper.selectCount(userQueryWrapper));

        List<UserVo> userVos = pageResult.getRecords().stream().map(user -> UserVo.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .createTime(user.getCreateTime())
                        .updateTime(user.getUpdateTime())
                        .createUser(user.getCreateUser())
                        .updateUser(user.getUpdateUser())
                        .build())
                .collect(Collectors.toList());

        return UserPageVo.builder()
                .userList(userVos)
                .total(pageResult.getTotal())
                .build();
    }

    @Override
    public boolean editUser(UserDto userDto) {
        User user = User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .password(bCryptPasswordEncoder.encode(userDto.getPassword()))
                .build();
        return userMapper.updateById(user) > 0;
    }

    @Override
    public boolean deleteUser(Long userId) {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", userId).set("is_deleted", null);
        return userMapper.update(new User(), wrapper) > 0;
    }

    @Override
    public boolean userRelation(UserRoleRelationDto userRoleRelationDto) {
        UserRole findUserRole = userRoleMapper.getUserRoleByUserRoleId(userRoleRelationDto.getUserId(), userRoleRelationDto.getRoleId());
        if (findUserRole != null) {
            throw new GlobalException(ErrorCodeEnum.B_User_Role_Repeat);
        }
        UserRole userRole = UserRole.builder()
                .userId(userRoleRelationDto.getUserId())
                .roleId(userRoleRelationDto.getRoleId())
                .build();
        return userRoleMapper.insert(userRole) > 0;
    }
}
