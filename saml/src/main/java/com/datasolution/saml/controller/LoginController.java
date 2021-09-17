package com.datasolution.saml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.datasolution.saml.service.LoginService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {

	private Environment environment;
	private LoginService loginService;

//	@Autowired
//	public LoginController(LoginService pLoginService, Environment pEnvironment) {
//		this.environment = pEnvironment;
//		this.loginService = pLoginService;
//	}

	@GetMapping("/ssoredirect")
	public String redirectToIDPWithAuthNRequest() {

		String redirectUrl, redirectString = null;

		String idpAppURL = environment.getProperty("IDP_SSO_URL");
		String relayState = environment.getProperty("RELAYSTATE_BASE_URL") + "?articleId=1234"; // Relaystate can be
		// dynamic
		String assertionConsumerServiceUrl = environment.getProperty("ACS_URL");
		String issuerId = environment.getProperty("IDP_ISSUER_ID");
		redirectUrl = loginService.getAuthNRedirectUrl(idpAppURL, relayState, assertionConsumerServiceUrl, issuerId);

		log.info("Redirecting to " + redirectUrl + " for applicationName:"
				+ environment.getProperty("RELAYSTATE_BASE_URL"));

//		return "redirect:" + redirectUrl;
		return "home";
	}
}
