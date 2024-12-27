package com.washy.springtest.pojo.dto.user;

import com.washy.springtest.pojo.dto.common.BasePageListDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: washy
 * @Date: 2024/08/05/16:44
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class UserPageDto extends BasePageListDto {

    /**
     * 起始时间
     */
    private LocalDate startTime;

    /**
     * 结束时间
     */
    private LocalDate endTime;

    /**
     * 查询
     */
    private String querySearch;
}
