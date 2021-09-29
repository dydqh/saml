package com.datasolution.saml.service;

import java.util.Base64;
import java.util.Base64.Decoder;

import org.springframework.stereotype.Service;

@Service
public class CryptionServiceImpl implements CryptionService {
	
	@Override
	public String decodeByBase64(String target) {
		Decoder decoder = Base64.getDecoder();
		byte[] decodedBytes = decoder.decode(target.getBytes());
		String decodedTarget = new String(decodedBytes);
		return decodedTarget;
	}
	
}
