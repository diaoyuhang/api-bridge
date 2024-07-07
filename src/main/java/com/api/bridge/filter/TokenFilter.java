package com.api.bridge.filter;

import com.api.bridge.constant.BaseConstant;
import com.api.bridge.utils.ReqThreadInfoUtil;
import com.api.bridge.utils.SecretUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class TokenFilter implements Filter {

    private Set<String> excludedAccuratePaths;

    private List<Pattern> excludedVaguePaths;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String paths = filterConfig.getInitParameter("excludedAccuratePaths");
        excludedAccuratePaths = new HashSet<>(Arrays.asList(paths.split(BaseConstant.COMMA_SEPARATOR)));

        String vaguePaths = filterConfig.getInitParameter("excludedVaguePaths");
        excludedVaguePaths = new ArrayList<>();
        for (String path : vaguePaths.split(BaseConstant.COMMA_SEPARATOR)) {
            excludedVaguePaths.add(Pattern.compile(path));
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();
        try {
            String token = request.getHeader("token");
            if (!isAuthWhiteList(requestURI)) {
                if (StringUtils.isBlank(token)) {
                    try {
                        String decrypt = SecretUtil.decrypt(token);
                    } catch (Exception e) {
                        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                    }
                    return;
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

    public Boolean isAuthWhiteList(String uri){
        if (excludedAccuratePaths.contains(uri)){
            return true;
        }

        for (Pattern pattern : excludedVaguePaths) {
            if (pattern.matcher(uri).matches()) {
                return true;
            }
        }

        return false;
    }
}
