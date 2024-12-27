package com.washy.springtest.pojo.vo.role;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
/**
 * Created with IntelliJ IDEA.
 *
 * @Author: washy
 * @Date: 2024/07/11/9:07
 */
@Data
@Builder
public class RoleInfoVo implements Serializable {
    private static final long serialVersionUID = 3826318338395148437L;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色编码
     */
    private String roleCode;
}
