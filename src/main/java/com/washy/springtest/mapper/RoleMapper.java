package com.washy.springtest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.washy.springtest.entity.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: washy
 * @Date: 2024/07/08/16:41
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}
