package com.washy.springtest.service;

import com.washy.springtest.entity.User;
import com.washy.springtest.pojo.dto.user.UserDto;
import com.washy.springtest.pojo.dto.user.UserPageDto;
import com.washy.springtest.pojo.dto.user.UserRoleRelationDto;
import com.washy.springtest.pojo.vo.user.UserInfoVo;
import com.washy.springtest.pojo.vo.user.UserPageVo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: washy
 * @Date: 2024/06/26/13:53
 */
public interface UserService {

    /**
     * 创建用户
     * @param userDto 当前用户
     * @return 是否成功
     */
    boolean createUser(UserDto userDto);

    /**
     * 登录
     * @param userDto 当前用户
     * @return Result
     */
    UserInfoVo login(UserDto userDto);

    /**
     * 获取用户分页
     *
     * @param userPageDto 用户分页
     * @return 是否成功
     */
    UserPageVo getUserPage(UserPageDto userPageDto);

    /**
     * 修改用户信息
     * @param userDto 用户信息
     * @return true or false
     */
    boolean editUser(UserDto userDto);

    /**
     * 删除用户
     * @param userId 用户id
     * @return 是否成功
     */
    boolean deleteUser(Long userId);

    /**
     * 建立用户角色关联
     * @param userRoleRelationDto 用户
     * @return 关联建立是否成功
     */
    boolean userRelation(UserRoleRelationDto userRoleRelationDto);
}
