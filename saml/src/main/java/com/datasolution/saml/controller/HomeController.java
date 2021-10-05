package com.datasolution.saml.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.Document;

import com.datasolution.saml.service.ConvertService;
import com.datasolution.saml.service.CryptionService;
import com.datasolution.saml.service.LoginService;

@Controller
@PropertySource("classpath:properties/application.properties")
public class HomeController {

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
	public String redirectToIDPWithAuthNRequest() {

		String redirectUrl = loginService.getAuthNRedirectUrl(idpAppURL, relayState, assertionConsumerServiceUrl,
				issuerId);
		return "redirect:" + redirectUrl;
	}
	
	@GetMapping("/ssoredirect2")
	public String redirectToIDPWithAuthNRequest2() {

		String idpAppURL2 = "http://localhost:8180/saml_idp/login";
		String redirectUrl = loginService.getAuthNRedirectUrl(idpAppURL2, relayState, assertionConsumerServiceUrl,
				issuerId);
		
		return "redirect:" + redirectUrl;
	}

	@GetMapping("/")
	public String loginSuccess() {
		return "home";
	}

	@Autowired
	private ConvertService convertService;

	@Autowired
	private CryptionService cryptionService;

	@PostMapping("/")
	public String loginSuccess(@RequestParam(required = false) String SAMLResponse, HttpSession session, Model model) {

		String decodedSAML = cryptionService.decodeByBase64(SAMLResponse);
		Document doc = convertService.convertStringToXMLDocument(decodedSAML);
		String assertionId = convertService.getValueByKeyInTag(doc, "saml2:Assertion", "ID");
		String spEntityId = convertService.getTextByTag(doc, "saml2:Audience");
		if (spEntityId.equals("SPEntityIdByDataSolution")) {
			session.setAttribute("AssertionId", assertionId);
			model.addAttribute("SpEntityId", spEntityId);
		}
		return "home";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.removeAttribute("AssertionId");
		return "redirect:/";
	}
}
