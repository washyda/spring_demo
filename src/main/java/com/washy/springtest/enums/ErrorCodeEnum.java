package com.washy.springtest.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ck
 */

@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {
    //参数 Param
    P_REQUIRED_NOT_FOUND("请输入必填参数", 404),
    S_ADD_FAID("新增失败", 405),
    S_UPDATE_FAID("更新失败", 405),


    //业务 Biz
    B_User_Repeat("用户已存在!", 1),
    B_Create_User("注册用户错误!", 2),
    B_Not_Found_User("用户不存在!", 3),
    B_Password_Error("密码错误!", 4),
    B_User_Role_Repeat("用户角色已建立关联!", 5)
    ;

    private final String message;
    private final int code;


}
