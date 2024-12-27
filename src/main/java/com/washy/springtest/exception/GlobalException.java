package com.washy.springtest.exception;

import com.washy.springtest.enums.ErrorCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * @author ck
 */
@Getter
@EqualsAndHashCode(callSuper = true)
@Data
public class GlobalException extends RuntimeException {
    private static final long serialVersionUID = -225437182049950297L;
    private String message;
    private Integer code;

    public GlobalException(String message) {
        this.message = message;
        this.code = 1;
    }

    public GlobalException(ErrorCodeEnum errorCode) {
        this.message = errorCode.getMessage();
        this.code = errorCode.getCode();
    }

}

