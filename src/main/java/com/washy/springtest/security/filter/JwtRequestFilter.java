package com.washy.springtest.security.filter;

import com.mysql.cj.util.StringUtils;
import com.washy.springtest.security.pojo.UserInfo;
import com.washy.springtest.utils.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: washy
 * @Date: 2024/07/02/14:50
 */

@Component
@AllArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 对于非白名单的请求，继续进行JWT验证
        String jwt = getJwtFromRequest(request);
        if (!StringUtils.isNullOrEmpty(jwt) && JwtUtils.validateToken(jwt)) {
            UserInfo userInfo = UserInfo.builder()
                    .userId(JwtUtils.getUserId(jwt))
                    .nickName(JwtUtils.getNickName(jwt))
                    .build();
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userInfo, null, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        return request.getHeader("Token");
    }
}
