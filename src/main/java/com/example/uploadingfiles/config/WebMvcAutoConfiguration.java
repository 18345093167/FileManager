package com.example.uploadingfiles.config;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@Configuration
public class WebMvcAutoConfiguration  extends WebMvcConfigurerAdapter implements ApplicationContextAware {

	private final ResourceProperties resourceProperties = new ResourceProperties();
	private Logger logger = LoggerFactory.getLogger(WebMvcAutoConfiguration.class);
	private ApplicationContext applicationContext;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new ReqInterceptor()).addPathPatterns("/**");
        //registry.addInterceptor(new LoginInterceptor()).excludePathPatterns("/index.html","/login");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
      //  registry.addViewController( "/").setViewName("index");
      //  registry.addViewController( "/login").setViewName("index");
    }

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		
	}

}