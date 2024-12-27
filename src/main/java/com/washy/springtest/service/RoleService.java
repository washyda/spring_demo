package com.washy.springtest.service;

import com.washy.springtest.pojo.dto.role.RoleDto;
import com.washy.springtest.pojo.dto.role.RoleMenuRelationDto;
import com.washy.springtest.pojo.dto.role.RolePageDto;
import com.washy.springtest.pojo.vo.role.RolePageVo;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: washy
 * @Date: 2024/07/08/16:31
 */
public interface RoleService {
    public boolean createRole(RoleDto roleDto);

    public RolePageVo getRolePage(RolePageDto rolePageDto);

    public boolean deleteRole(Long roleId);

    public boolean createRoleMenuRelation(RoleMenuRelationDto roleMenuRelationDto);
}
