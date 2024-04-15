package com.api.bridge.config;

import com.api.bridge.filter.TokenFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterRegistrationBeanConfig {

    @Bean
    public FilterRegistrationBean<TokenFilter> registerTokenFilter(){
        FilterRegistrationBean<TokenFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new TokenFilter());
        bean.setName("tokenFilter");
        bean.addUrlPatterns("/*");
        bean.setOrder(1);
        bean.addInitParameter("excludedPaths","/user/register");//需要排除的uri
        return bean;
    }
}
