package com.api.bridge.filter;

import com.api.bridge.utils.ReqThreadInfoUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
public class TokenFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            String token = request.getHeader("token");
            if (StringUtils.isBlank(token)){
                throw new RuntimeException("获取身份失败");
            }
            String requestURI = request.getRequestURI();
            ReqThreadInfoUtil.setToken(token);
            // 要继续处理请求，必须添加 filterChain.doFilter()
            filterChain.doFilter(servletRequest,servletResponse);
        } catch (Exception e) {
            throw e;
        }finally {
            ReqThreadInfoUtil.removeToken();
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
