package com.datasolution.saml_idp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.datasolution.saml_idp.vo.IdpResponseVo;

@Controller
public class LoginController {
	
	@GetMapping("/")
	public String login() {
		return "login";
	}
	
	@PostMapping("/")
	public String redirect() {
		return "redirect:http://localhost:8080/saml";
	}
	
}
