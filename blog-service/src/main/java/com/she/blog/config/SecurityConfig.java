package com.she.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer; // 导入 Customizer

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    // 定义 PasswordEncoder Bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // 禁用 CSRF 防护，方便 API 测试，生产环境���要考虑
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/user/login", "/user/all").permitAll() // 允许登录和获取所有用户的接口匿名访问
                        .anyRequest().authenticated() // 其他所有请求都需要认证
                )
                .formLogin(Customizer.withDefaults()) // 启用默认的表单登录
                .httpBasic(Customizer.withDefaults()); // 启用 HTTP Basic 认证
        return http.build();
    }


}
