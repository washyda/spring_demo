package com.washy.springtest.security.pojo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: washy
 * @Date: 2024/07/03/8:12
 */
@Data
@Builder
public class UserInfo implements Serializable {

    private static final long serialVersionUID = -440108688361397045L;

    private Long userId;

    private String nickName;
}
