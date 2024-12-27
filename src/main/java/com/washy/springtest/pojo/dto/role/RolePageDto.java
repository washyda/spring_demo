package com.washy.springtest.pojo.dto.role;

import com.washy.springtest.pojo.dto.common.BasePageListDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: washy
 * @Date: 2024/08/09/16:30
 */
@Builder
@Data
@AllArgsConstructor
public class RolePageDto extends BasePageListDto {
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
