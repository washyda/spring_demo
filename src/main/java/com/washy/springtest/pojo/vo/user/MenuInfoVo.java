package com.washy.springtest.pojo.vo.user;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: washy
 * @Date: 2024/07/17/15:52
 */
@Data
@Builder
public class MenuInfoVo implements Serializable {

    private static final long serialVersionUID = -4511960578513425278L;
    /**
     * 菜单id
     */
    private Long id;

    /**
     * 父菜单id
     */
    private Long parentId;

    /**
     * 路由名称
     */
    private String routeName;

    /**
     * 路径
     */
    private String path;

    /**
     * 路由路径
     */
    private String routePath;
}
