package com.datasolution.saml.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.datasolution.saml.service.LoginService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@PropertySource("classpath:properties/application.properties")
public class LoginController {

	// 로그인 요청을 처리하는 SAML IdP의 URL
	@Value("${IDP_SSO_URL}")
	private String idpAppURL;

	// 인증 과정이 완료된 후 사용자의 리디렉션 URL
	@Value("${RELAYSTATE_BASE_URL}")
	private String relayState;

	// Id 공급자가 인증 응답과 함께 리디렉션되는 서비스 공급자의 끝점
	@Value("${ACS_URL}")
	private String assertionConsumerServiceUrl;

	// SAML 엔티티의 고유 식별자
	@Value("${IDP_ISSUER_ID}")
	private String issuerId;

	@Autowired
	private LoginService loginService;

	@GetMapping("/ssoredirect")
	public String redirectToIDPWithAuthNRequest(HttpSession session) {

		String redirectUrl = loginService.getAuthNRedirectUrl(idpAppURL, relayState, assertionConsumerServiceUrl,
				issuerId);

		log.info("Redirecting to " + redirectUrl + " for applicationName:" + relayState);

		System.out.println(redirectUrl);
		System.out.println("11111111111111111111");
		return "redirect:" + redirectUrl;
	}
}
