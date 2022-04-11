package com.cos.security1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.security1.config.oauth.PrincipalOauth2UserService;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록이 된다.
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // secured 어노테이션 활성화, preAuthorize 어노테이션 활성화 
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private PrincipalOauth2UserService principalOauth2UserService;
	
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
		.defaultSuccessUrl("/") // 로그인하면 메인페이지로 이동.
		.and()
		.oauth2Login() // 구글 oauth2 로그인
		.loginPage("/loginform")
		// 구글 로그인이 완료된 이후의 후처리 필요 (구글과 같은 Oauth provider를 사용하면 아래의 1번은 생략되어 편리)
		// 1. 코드받기(사용자가 정상적인 로그인한 사용자임을 인증) 2. 엑세스 토큰 받기(사이트 사용자에 접근할 수 있는 권한 생김) 
		// 3. 사용자 프로필 정보를 가져옴 4. 그 정보로 자동으로 회원가입 진행 가능
		.userInfoEndpoint()
		.userService(principalOauth2UserService); // 들어가야 할 타입이 OAuth2UserService
	}
}
