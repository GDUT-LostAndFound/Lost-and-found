package com.example.interceptor;

import com.example.model.HostHolder;
import com.example.util.JwtUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;

/**
 * 用户拦截器
 *
 * @author DF.
 * @date 2023-05-20 21:37:59
 */
@Component
public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
        String token = request.getHeader("Authorization");
        //判断token是否无效（乱写的）
        Integer id = JwtUtil.parseJWT(token.substring(7));
        HostHolder.setUserId(id);
        return true;
    }

    //移除用户信息
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HostHolder.clear();
    }
}
