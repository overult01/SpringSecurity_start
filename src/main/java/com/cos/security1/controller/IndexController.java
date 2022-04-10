package com.cos.security1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // 뷰 반환
public class IndexController {

	@GetMapping({"", "/"})
	public String index() {
		// mustache(스프링에서 권장하는 템플릿엔진. jsp 대신)
		// 기본폴더: src/main/resources/ 
		// 뷰리졸버 설정: templates(prefix), .mustache(suffix)
		return "index";
	}
	
	// 디폴트 설정으로는 스프링 시큐리티가 가로챈다.(설정을 추후 바꿔주어야 한다)
	@GetMapping("/login")
	public @ResponseBody String login() {
		return "login";
	}
	
	@GetMapping("/user")
	public @ResponseBody String user() {
		return "user";
	}
	
	@GetMapping("/admin")
	public @ResponseBody String admin() {
		return "admin";
	}
	
	@GetMapping("/menager")
	public @ResponseBody String manager() {
		return "manager";
	}
	
	@GetMapping("/join")
	public @ResponseBody String join() {
		return "join";
	}
	
	@GetMapping("/joinProc")
	public @ResponseBody String loginProc() {
		return "회원가입 완료됨!";
	}

}
