package com.cos.security1.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.security1.model.User;
import com.cos.security1.repository.UserRepository;

// 앞서 시큐리티 설정에서 loginProcessingUrl("/login") 설정해둠
// 따라서 /login 요청 오면 자동으로 UserDetailsService 타입으로 IoC되어있는 loadUserByUsername 함수가 실행 (규칙) 
@Service
public class PrincipalDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	// 시큐리티 session(내부 Authentication (내부 UserDetails))
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("username: " + username);
		User userEntity = userRepository.findByUsername(username);
		if(userEntity != null) {
			System.out.println("로그인중");
			return new PrincipalDetails(userEntity);
		}
		System.out.println("로그인실패");
		return null;
	}

	
}
