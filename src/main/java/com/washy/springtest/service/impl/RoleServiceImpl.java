package com.washy.springtest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.washy.springtest.entity.Role;
import com.washy.springtest.entity.RoleMenu;
import com.washy.springtest.mapper.RoleMapper;
import com.washy.springtest.mapper.RoleMenuMapper;
import com.washy.springtest.mapper.UserRoleMapper;
import com.washy.springtest.pojo.dto.role.RoleDto;
import com.washy.springtest.pojo.dto.role.RoleMenuRelationDto;
import com.washy.springtest.pojo.dto.role.RolePageDto;
import com.washy.springtest.pojo.vo.role.RoleInfoVo;
import com.washy.springtest.pojo.vo.role.RolePageVo;
import com.washy.springtest.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: washy
 * @Date: 2024/07/08/16:31
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Override
    public boolean createRole(RoleDto roleDto) {
        Role role = Role.builder()
                .roleName(roleDto.getRoleName())
                .roleCode(roleDto.getRoleCode())
                .build();
        return roleMapper.insert(role) > 0;
    }

    @Override
    public RolePageVo getRolePage(RolePageDto rolePageDto) {
        Page<Role> rolePage = new Page<>(rolePageDto.getPageNum(), rolePageDto.getPageSize());
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        roleQueryWrapper.orderByAsc("id");
        if(StringUtils.isNotEmpty(rolePageDto.getQuerySearch())) {
            roleQueryWrapper.like("role_name", rolePageDto.getQuerySearch());
        }

        if(rolePageDto.getStartTime() != null && rolePageDto.getEndTime() != null) {
            roleQueryWrapper.between("create_time", rolePageDto.getStartTime(), rolePageDto.getEndTime());
        }

        Page<Role> pageResult = roleMapper.selectPage(rolePage, roleQueryWrapper);
        pageResult.setTotal(roleMapper.selectCount(roleQueryWrapper));

        List<RoleInfoVo> roleVos = pageResult.getRecords().stream().map(role -> RoleInfoVo.builder()
                        .roleId(role.getId())
                        .roleName(role.getRoleName())
                        .roleCode(role.getRoleCode())
                        .build())
                .collect(Collectors.toList());

        return RolePageVo.builder()
                .roleList(roleVos)
                .total(pageResult.getTotal())
                .build();
    }

    @Override
    @Transactional
    public boolean deleteRole(Long roleId) {
        // 删除角色人员关联
        userRoleMapper.updateUserRolesByRoleId(roleId);
        // 删除角色菜单关联
        roleMenuMapper.updateMenusByRoleId(roleId);
        // 删除角色
        Role role = Role.builder().id(roleId).isDeleted(true).build();
        int rows = roleMapper.updateById(role);
        return rows > 0;
    }

    @Override
    public boolean createRoleMenuRelation(RoleMenuRelationDto roleMenuRelationDto) {
        RoleMenu roleMenu = RoleMenu.builder()
                .roleId(roleMenuRelationDto.getRoleId())
                .menuId(roleMenuRelationDto.getMenuId())
                .build();
        int rows = roleMenuMapper.insert(roleMenu);
        return rows > 0;
    }
}
