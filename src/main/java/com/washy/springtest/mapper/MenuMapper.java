package com.washy.springtest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.washy.springtest.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: washy
 * @Date: 2024/07/17/16:03
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
}
