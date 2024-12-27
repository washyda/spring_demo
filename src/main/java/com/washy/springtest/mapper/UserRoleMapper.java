package com.washy.springtest.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.washy.springtest.entity.RoleMenu;
import com.washy.springtest.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: washy
 * @Date: 2024/07/09/16:35
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
    default UserRole getUserRoleByUserRoleId(Long userId, Long roleId) {
        LambdaQueryWrapper<UserRole> userRoleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userRoleLambdaQueryWrapper.eq(UserRole::getUserId, userId).eq(UserRole::getRoleId, roleId).eq(UserRole::getIsDeleted, 0);
        return selectOne(userRoleLambdaQueryWrapper);
    }

    default List<UserRole> getUserRolesByUserId(Long userId) {
        return selectList(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, userId).eq(UserRole::getIsDeleted, 0));
    }

    default void updateUserRolesByRoleId(Long roleId) {
        update(new UserRole(), new UpdateWrapper<UserRole>().set("is_deleted", 1).eq("role_id", roleId));
    }
}
