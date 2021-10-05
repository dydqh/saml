package com.datasolution.saml_idp.controller;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String login(HttpSession session) {
		if(session.getAttribute("uuid") != null) {
			return "redirect:http://localhost:8080/saml";
		}
		return "login";
	}
	
	@PostMapping("/login")
	public String login(HttpSession session, @RequestParam String id, @RequestParam String password, @RequestParam(required = false) String SAMLRequest) {
		if(id.equals("admin") && password.equals("admin")) {
			String uuid = UUID.randomUUID().toString();
			session.setAttribute("uuid", uuid);
		}
		if(SAMLRequest == null) {
			return "home";
		}
		return "redirect:http://localhost:8080/saml";
	}
	
}
