package com.cos.security1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.security1.model.User;

// JpaRepository: 1. 기본적인 CRUD함수를 들고 있음. 2. @Repository를 붙이지 않아도 IoC된다(JpaRepository를 상속해서)
public interface UserRepository extends JpaRepository<User, Integer>{

	// findBy(규칙) + Username(문법)
	// 메서드가 호출되면 select * from user where username = 1? 이 호출된다.
	public User findByUsername(String username); // JPA Query methods

}
