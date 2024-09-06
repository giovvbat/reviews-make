package com.giovanna.reviewsmake.infra.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
/*pagination-dto config*/
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class CustomWebConfigurer implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        PageableHandlerMethodArgumentResolver pageableResolver = new PageableHandlerMethodArgumentResolver();
        pageableResolver.setFallbackPageable(PageRequest.of(0, 2));
        argumentResolvers.add(pageableResolver);
    }
}