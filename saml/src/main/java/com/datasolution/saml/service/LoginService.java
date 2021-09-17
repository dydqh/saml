package com.datasolution.saml.service;

public interface LoginService {

	String getAuthNRedirectUrl(String idpAppURL, String relayState, String assertionConsumerServiceUrl,
			String issuerId);
}
