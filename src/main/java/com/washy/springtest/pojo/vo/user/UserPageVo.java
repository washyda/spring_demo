package com.washy.springtest.pojo.vo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: washy
 * @Date: 2024/08/06/9:11
 */
@Builder
@Data
@AllArgsConstructor
public class UserPageVo implements Serializable {

    private static final long serialVersionUID = -6669797975818817037L;

    private List<UserVo> userList;

    private Long total;
}
