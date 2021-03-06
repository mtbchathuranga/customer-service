package com.fidenz.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.fidenz")
public class AppConfig implements WebMvcConfigurer {
   @Bean
    public InternalResourceViewResolver viewResolver(){
       InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
       viewResolver.setPrefix("/");
       return viewResolver;
   }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
       configurer.enable();
    }
}
