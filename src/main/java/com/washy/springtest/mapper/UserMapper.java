package com.washy.springtest.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.washy.springtest.entity.User;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author washy
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    default User getUserByUsername(String username) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUsername, username).eq(User::getIsDeleted, 0);
        return selectOne(userLambdaQueryWrapper);
    }
}