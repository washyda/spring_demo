package com.washy.springtest.pojo.vo.role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: washy
 * @Date: 2024/08/09/16:31
 */
@Builder
@Data
@AllArgsConstructor
public class RolePageVo implements Serializable {
    private static final long serialVersionUID = 5887702208042615365L;

    /**
     * 总数
     */
    private Long total;

    /**
     * 角色列表
     */
    private List<RoleInfoVo> roleList;
}
