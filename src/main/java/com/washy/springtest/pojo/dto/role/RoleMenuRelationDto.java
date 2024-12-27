package com.washy.springtest.pojo.dto.role;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: washy
 * @Date: 2024/07/17/15:41
 */
@Data
public class RoleMenuRelationDto implements Serializable {
    private static final long serialVersionUID = 8881392420659592430L;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 菜单id
     */
    private Long menuId;
}
