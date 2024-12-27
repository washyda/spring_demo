package com.washy.springtest.pojo.dto.role;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: washy
 * @Date: 2024/07/08/16:30
 */
@Data
public class RoleDto {
    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色代码
     */
    private String roleCode;
}
