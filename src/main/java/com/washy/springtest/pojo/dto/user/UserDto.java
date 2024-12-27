package com.washy.springtest.pojo.dto.user;

import com.washy.springtest.pojo.dto.role.RoleDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: washy
 * @Date: 2024/06/27/14:37
 */
@Data
public class UserDto implements Serializable {
    private static final long serialVersionUID = -3300594488382846522L;

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空!")
    private String username;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空!")
    private String password;

    /**
     * 角色列表
     */
    private List<RoleDto> roles;

}
