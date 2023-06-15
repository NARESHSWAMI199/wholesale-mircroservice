package com.auth.config;

import com.auth.MyInterceptor;
import com.auth.config.jwt.JwtTokenUtil;
import com.auth.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorHandler implements WebMvcConfigurer {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserRepository userRepository;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(new MyInterceptor(jwtTokenUtil,userRepository)).excludePathPatterns(
                "/**"
        );
    }
}
