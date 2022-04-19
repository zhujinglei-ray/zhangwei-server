package com.zhangwei.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

//设置允许跨域
@Configuration
public class WebMvcConfig {
    /**
     * 允许跨域访问
     */
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 可限制哪个请求可以通过跨域
                .allowedHeaders("*")  // 可限制固定请求头可以通过跨域
                .allowedMethods("*") // 可限制固定methods可以通过跨域
                .allowedOrigins("*");  // 可限制访问ip可以通过跨域
    }
}
