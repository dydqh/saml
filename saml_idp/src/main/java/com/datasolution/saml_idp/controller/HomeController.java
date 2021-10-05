package com.datasolution.saml_idp.controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.opensaml.DefaultBootstrap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@PostConstruct
	public void postProcessBeanFactory(){
        try {
            DefaultBootstrap.bootstrap();
            System.out.println("셋팅 완료");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("uuid");
		return "redirect:/";
	}
}
