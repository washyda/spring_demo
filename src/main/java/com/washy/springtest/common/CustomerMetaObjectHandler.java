package com.washy.springtest.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.washy.springtest.security.pojo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: washy
 * @Date: 2024/07/06/8:49
 */
@Slf4j
@Component
public class CustomerMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "createUser", Long.class, getCurrentUserId());
        this.strictInsertFill(metaObject, "updateUser", Long.class, getCurrentUserId());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updateUser", Long.class, getCurrentUserId());
    }

    /**
     * 获取当前登录用户的ID。
     * 假设用户实体类中包含userId属性。
     *
     * @return 当前登录用户的ID，如果没有登录用户则返回null
     */
    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !authentication.getName().equals("anonymousUser")) {
            // 假设getUserId是自定义UserDetails实现中获取userId的方法
            return ((UserInfo) authentication.getPrincipal()).getUserId();
        }
        return 0L;
    }
}
