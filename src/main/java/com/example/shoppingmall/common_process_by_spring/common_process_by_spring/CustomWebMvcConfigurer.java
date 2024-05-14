package com.example.shoppingmall.common_process_by_spring.common_process_by_spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class CustomWebMvcConfigurer extends WebMvcConfigurationSupport {

   @Override
   protected void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(new MyInterceptor())
            .addPathPatterns("/**");
   }
}
