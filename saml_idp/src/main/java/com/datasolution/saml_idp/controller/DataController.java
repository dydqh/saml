package com.datasolution.saml_idp.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class DataController {
	
	@PostMapping("/getSAMLResponse")
	public String getSAMLResponse() {
		String SAMLResponse = "aa";
		return SAMLResponse;
	}
	
	@PostMapping("/getRelayState")
	public String getRelayState() {
		String RelayState = "bb";
		return RelayState;
	}
	
}
