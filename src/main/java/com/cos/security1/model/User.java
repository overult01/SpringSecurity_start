package com.cos.security1.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // 해당 클래스를 테이블로 인식할 수 있도록 만드는 어노테이션
@Data
@NoArgsConstructor // 기본 생성자 
public class User {

	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 매핑 전략을 IDENTITY로 설정.
	private int id;
	private String username;
	private String password;
	private String email;
	private String role;
	
	private String provider; // 구글 
	private String providerId; // 구글 등에서 받은 식별자
	
	@CreationTimestamp // 해당 컬럼의 기본값을 해당 데이터가 생성된 시각으로 지정.
	private Timestamp createDate;

	@Builder
	public User(String username, String password, String email, String role, String provider, String providerId,
			Timestamp createDate) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.provider = provider;
		this.providerId = providerId;
		this.createDate = createDate;
	}
	
}
// 스프링 부트에서 DB테이블 만들기: https://velog.io/@deannn/Spring-Boot-Blog-Project-DB-%ED%85%8C%EC%9D%B4%EB%B8%94-%EB%A7%8C%EB%93%A4%EA%B8%B0