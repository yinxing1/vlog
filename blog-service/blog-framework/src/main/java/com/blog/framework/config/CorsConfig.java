package com.blog.framework.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许所有路径的跨域请求
        registry.addMapping("/**")
                // 允许所有来源 (生产环境建议配置为具体的前端域名)
                .allowedOriginPatterns("*")
                // 允许所有请求方法 (GET, POST, PUT, DELETE, etc.)
                .allowedMethods("*")
                // 允许携带凭证 (如Cookie)
                .allowCredentials(true)
                // 预检请求的有效期，单位秒
                .maxAge(3600)
                // 允许所有请求头
                .allowedHeaders("*");
    }
}