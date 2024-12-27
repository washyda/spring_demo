package com.washy.springtest.pojo.vo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: washy
 * @Date: 2024/08/06/9:00
 */
@Builder
@AllArgsConstructor
@Data
public class UserVo implements Serializable {

    private static final long serialVersionUID = 1396085473142247379L;

    private Long id;

    /**
     * 用户名
     */
    private String username;

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

}
