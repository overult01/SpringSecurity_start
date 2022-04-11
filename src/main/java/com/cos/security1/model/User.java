package com.cos.security1.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity // 해당 클래스를 테이블로 인식할 수 있도록 만드는 어노테이션
@Data
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
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	
	
}
// 스프링 부트에서 DB테이블 만들기: https://velog.io/@deannn/Spring-Boot-Blog-Project-DB-%ED%85%8C%EC%9D%B4%EB%B8%94-%EB%A7%8C%EB%93%A4%EA%B8%B0