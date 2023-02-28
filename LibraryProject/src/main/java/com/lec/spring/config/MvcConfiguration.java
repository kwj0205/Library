package com.lec.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// resource 경로 설정
@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

        @Override
        public void addResourceHandlers(final ResourceHandlerRegistry registry) {
            System.out.println("\tLocalMvcConfiguration.addResourceHandlers() 호출");

            registry
                    .addResourceHandler("/**")
                    .addResourceLocations("classpath:/templates/", "classpath:/static/")
                    ;
        }
    }

















