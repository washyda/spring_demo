package com.washy.springtest.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
 * @Date: 2024/07/17/15:09
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    default List<RoleMenu> getMenusByRoleIds(List<Long> roleIds) {
        QueryWrapper<RoleMenu> roleMenuQueryWrapper = new QueryWrapper<>();
        roleMenuQueryWrapper.lambda().in(RoleMenu::getRoleId, roleIds);
        return selectList(roleMenuQueryWrapper);
    }

    default void updateMenusByRoleId(Long roleId) {
        update(new RoleMenu(), new UpdateWrapper<RoleMenu>().set("is_deleted", 1).eq("role_id", roleId));
    }
}
