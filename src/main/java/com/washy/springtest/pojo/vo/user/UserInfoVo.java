package com.washy.springtest.pojo.vo.user;

import com.washy.springtest.pojo.vo.role.RoleInfoVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: washy
 * @Date: 2024/07/02/10:25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVo implements Serializable {

    private static final long serialVersionUID = 8198771365856652830L;

    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 主题配置
     */
    private Long themeConfig;

    /**
     * 创建人
     */
    private Long createUser;

    /**
     * 更新人
     */
    private Long updateUser;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 用户token
     */
    private String token;

    /**
     * 角色列表
     */
    private List<RoleInfoVo> roleList;

    /**
     * 菜单列表
     */
    private List<MenuInfoVo> menuList;
}
