package com.cos.security1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록이 된다.
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // secured 어노테이션 활성화, preAuthorize 어노테이션 활성화 
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean // 해당 메서드의 리턴되는 오브젝트를 IoC로 등록 
	public BCryptPasswordEncoder encodePwd() { // 비밀번호 암호화하려고 
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
		.antMatchers("/user/**").authenticated() // 이런 주소로 들어오면 인증 필요(로그인 한 사람만 들어올 수 있다)
		.antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')") // 로그인했지만 admin, manager만 들어올 수 있
		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") // 로그인한 admin만 들어올 수 있다.
		.anyRequest().permitAll() // (위 3개 외) 다른 요청은 모두 허용
		.and()
		.formLogin()
		.loginPage("/loginform") // 권한이 없는 페이지로 가려고 하면 login페이지로 보내기
		.loginProcessingUrl("/login") // /login이 호출되면 시큐리티가 낚아채셔 대신 로그인 진행 
		.defaultSuccessUrl("/"); // 로그인하면 메인페이지로 이동.
	}
}
