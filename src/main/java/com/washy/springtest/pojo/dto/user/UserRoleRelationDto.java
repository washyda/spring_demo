package com.washy.springtest.pojo.dto.user;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: washy
 * @Date: 2024/07/09/16:15
 */
@Data
public class UserRoleRelationDto {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 角色id
     */
    private Long roleId;
}
