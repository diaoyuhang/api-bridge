package com.api.bridge.filter;

import com.api.bridge.constant.BaseConstant;
import com.api.bridge.utils.ReqThreadInfoUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TokenFilter implements Filter {

    private Set<String> excludedPaths;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String paths = filterConfig.getInitParameter("excludedPaths");
         excludedPaths = new HashSet<>(Arrays.asList(paths.split(BaseConstant.COMMA_SEPARATOR)));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();
        try {
            String token = request.getHeader("token");
            if (!excludedPaths.contains(requestURI)) {
                if (StringUtils.isBlank(token)) {
                    throw new RuntimeException("获取身份失败");
                }
            }

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
