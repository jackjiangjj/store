package com.jw.store.common.config;

import com.google.gson.Gson;
import com.jw.store.common.resolver.FilterHanderMethodArgumentResolver;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer , ApplicationContextAware {
    private static Gson generation;
    private static ApplicationContext context;
    @Override//完成跨域访问
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
          context=applicationContext;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new FilterHanderMethodArgumentResolver());
    }
}
