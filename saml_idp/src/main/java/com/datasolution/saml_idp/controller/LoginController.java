package com.datasolution.saml_idp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.datasolution.saml_idp.vo.IdpResponseVo;

@Controller
public class LoginController {
	
	@GetMapping("/")
	public String login(HttpSession session) {
		if(session.getAttribute("isLogin") == null) {
			return "login";
		}
		return "redirect:http://localhost:8080/saml";
	}
	
	@PostMapping("/")
	public String redirect(HttpSession session) {
		session.setAttribute("isLogin", true);
		return "redirect:http://localhost:8080/saml";
	}
	
}
