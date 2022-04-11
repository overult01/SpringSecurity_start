package com.cos.security1.config.oauth;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.cos.security1.config.auth.PrincipalDetails;
import com.cos.security1.config.oauth.provider.NaverUserInfo;
import com.cos.security1.config.oauth.provider.OAuth2UserInfo;
import com.cos.security1.model.User;
import com.cos.security1.repository.UserRepository;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

	@Autowired
	private UserRepository userRepository;

	// 구글로부터 받은 userRequest 데이터에 대한 후처리되는 함수 
	// userRequest 는 code를 받아서 accessToken을 응답 받은 객체
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		// 구글로 부터 전달받은 사용자정보 + 토큰
		System.out.println("getClientRegistration: " + userRequest.getClientRegistration()); // registrationId로 어떤 OAuth로 로그인했는지 확인 가능 
		System.out.println("getAccessToken: " + userRequest.getAccessToken().getTokenValue());
		// 구글 로그인 버튼 클릭 -> 구글 로그인 창 -> 로그인 완료 -> code 리턴(OAuth-Client라이브러리) -> Access Token 요청
		// userRequest(Access Token등 정보를 포함한) 정보 -> 구글로부터 회원 프로필 받아야 할 때 loadUser메서드 사용 
		System.out.println("getAttributes: " + super.loadUser(userRequest).getAttributes()); // 사용자 정보

		OAuth2User oAuth2User = super.loadUser(userRequest); // google의 회원 프로필 조회

		// code를 통해 구성한 정보
		System.out.println("userRequest clientRegistration : " + userRequest.getClientRegistration());
		// token을 통해 응답받은 회원정보
		System.out.println("oAuth2User : " + oAuth2User);
	
		return processOAuth2User(userRequest, oAuth2User);
	}

	private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {

		// Attribute를 파싱해서 공통 객체로 묶는다. 관리가 편함.
//		OAuth2UserInfo oAuth2UserInfo = null;
//		if (userRequest.getClientRegistration().getRegistrationId().equals("naver")){
//			System.out.println("네이버 로그인 요청~~");
//			oAuth2UserInfo = new NaverUserInfo((Map)oAuth2User.getAttributes().get("response"));
//		} else {
//			System.out.println("우리는 구글과 페이스북, 네이버만 지원해요 ㅎㅎ");
//		}

		//System.out.println("oAuth2UserInfo.getProvider() : " + oAuth2UserInfo.getProvider());
		//System.out.println("oAuth2UserInfo.getProviderId() : " + oAuth2UserInfo.getProviderId());
//		Optional<User> userOptional = 
//				userRepository.findByProviderAndProviderId(oAuth2UserInfo.getProvider(), oAuth2UserInfo.getProviderId());
		
//		User user;
//		if (userOptional.isPresent()) {
//			user = userOptional.get();
//			// user가 존재하면 update 해주기
//			user.setEmail(oAuth2UserInfo.getEmail());
//			userRepository.save(user);
//		} else {
//			// user의 패스워드가 null이기 때문에 OAuth 유저는 일반적인 로그인을 할 수 없음.
//			user = User.builder()
//					.username(oAuth2UserInfo.getProvider() + "_" + oAuth2UserInfo.getProviderId())
//					.email(oAuth2UserInfo.getEmail())
//					.role("ROLE_USER")
//					.provider(oAuth2UserInfo.getProvider())
//					.providerId(oAuth2UserInfo.getProviderId())
//					.build();
//			userRepository.save(user);
//		}

//		return new PrincipalDetails(user, oAuth2User.getAttributes());
		return super.loadUser(userRequest);
	}
}
