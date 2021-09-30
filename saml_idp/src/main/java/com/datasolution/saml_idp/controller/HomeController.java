package com.datasolution.saml_idp.controller;

import javax.annotation.PostConstruct;

import org.opensaml.DefaultBootstrap;
import org.opensaml.saml2.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.datasolution.saml_idp.service.CreateService;

//@Controller
public class HomeController {
	
	private final String entityId = "abcdabcdabcd";
	
	@PostConstruct
	public void postProcessBeanFactory(){
        try {
            DefaultBootstrap.bootstrap();
            System.out.println("셋팅 완료");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	@Autowired
	private CreateService createService;
	
//	@RequestMapping("/")
	public String home(Model model) {
//		Status status = createService.buildStatus(StatusCode.SUCCESS_URI);
//        Credential signingCredential = createService.resolveCredential(entityId);
		Response authResponse = createService.buildSAMLObject(Response.class, Response.DEFAULT_ELEMENT_NAME);
		model.addAttribute("AuthResponse", authResponse);
		model.addAttribute("Test", "test");
//		Issuer issuer = createService.buildIssuer(entityId);
//        authResponse.setIssuer(issuer);
//        authResponse.setID(createService.randomSAMLId());
//        authResponse.setIssueInstant(new DateTime());
//        authResponse.setInResponseTo(principal.getRequestID());
//        authResponse.setInResponseTo("aaaaaaaaaa");

//        Assertion assertion = buildAssertion(principal, status, entityId);
//        Assertion assertion = buildAssertion(principal, status, entityId);
//        signAssertion(assertion, signingCredential);
//        authResponse.getAssertions().add(assertion);
//        authResponse.setDestination(principal.getAssertionConsumerServiceUrl());
//        authResponse.setStatus(status);

//        Endpoint endpoint = createService.buildSAMLObject(Endpoint.class, SingleSignOnService.DEFAULT_ELEMENT_NAME);
//        endpoint.setLocation(principal.getAssertionConsumerServiceUrl());
//        HttpServletResponseAdapter outTransport = new HttpServletResponseAdapter(response, false);
		return "redirect:http://localhost:8080/saml/";
	}
	
}
