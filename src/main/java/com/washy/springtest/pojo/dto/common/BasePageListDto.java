package com.washy.springtest.pojo.dto.common;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: washy
 * @Date: 2024/08/05/16:45
 */
@Data
public class BasePageListDto {

    private Integer pageNum = 1;

    private Integer pageSize = 12;
}
