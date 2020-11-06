package com.jcy.jcyycback.interceptor;

import com.jcy.jcyycback.common.utils.CacheUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: PanYu
 * @create: 2020-11-06 16:55
 **/

@Configuration
@Slf4j
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(new String[]{"*"})
                .allowedMethods(new String[]{"GET", "POST", "OPTIONS"})
                .allowCredentials(true).maxAge(3600L);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> urls = (List<String>) CacheUtils.get("filterUrl");
        if (urls == null)
            urls = new ArrayList<>();
        registry.addInterceptor((HandlerInterceptor) this.loginInterceptor)
                .addPathPatterns(new String[]{"/**"})
                .excludePathPatterns(new String[]{"/sys/user/logout"})
                .excludePathPatterns(new String[]{"/sys/user/login"})
                .excludePathPatterns(new String[]{"/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**"})
                .excludePathPatterns(urls);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(new String[]{"/js/**"}).addResourceLocations(new String[]{"classpath:/js/"});
        registry.addResourceHandler(new String[]{"swagger-ui.html"}).addResourceLocations(new String[]{"classpath:/META-INF/resources/"});
        registry.addResourceHandler(new String[]{"/webjars/**"}).addResourceLocations(new String[]{"classpath:/META-INF/resources/webjars/"});
    }
}

