package com.cos.security1.config;

import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		MustacheViewResolver resolver = new MustacheViewResolver();
		resolver.setCharset("UTF-8");
		resolver.setContentType("text/html; charset=UTF-8");
		resolver.setPrefix("classpath:/templates/"); // classpath: 는 우리 프로젝트 
		resolver.setSuffix(".html"); // .html 파일을 만들어도 mustache가 인식 
		
		registry.viewResolver(resolver);
		
		// WebMvcConfigurer: 스프링시큐리티로 의존성을 설정하게 되면, 우리 홈페이지 홈페이지로 가는 모든 페이지가 막혀서 모든 페이지가 인증이 필요한 페이지로 바뀐다.
	}
}
