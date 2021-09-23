package com.datasolution.saml.service;

import java.util.UUID;

import org.joda.time.DateTime;
import org.opensaml.common.SAMLVersion;
import org.opensaml.saml2.core.AuthnContextClassRef;
import org.opensaml.saml2.core.AuthnContextComparisonTypeEnumeration;
import org.opensaml.saml2.core.AuthnRequest;
import org.opensaml.saml2.core.Issuer;
import org.opensaml.saml2.core.NameIDPolicy;
import org.opensaml.saml2.core.RequestedAuthnContext;
import org.opensaml.saml2.core.impl.AuthnContextClassRefBuilder;
import org.opensaml.saml2.core.impl.AuthnRequestBuilder;
import org.opensaml.saml2.core.impl.IssuerBuilder;
import org.opensaml.saml2.core.impl.NameIDPolicyBuilder;
import org.opensaml.saml2.core.impl.RequestedAuthnContextBuilder;

public class AuthNRequestBuilder {

	// NameID 정책
	private static final String SAML2_NAME_ID_POLICY = "urn:oasis:names:tc:SAML:2.0:nameid-format:persistent";
	
	private static final String SAML2_PROTOCOL = "urn:oasis:names:tc:SAML:2.0:protocol";
	// 프로토콜 바인딩 URI
	private static final String SAML2_POST_BINDING = "urn:oasis:names:tc:SAML:2.0:bindings:HTTP-POST";
	// SAML 인증 컨텍스트 클래스 중 암호로 보호된 전송 인증 방법
	private static final String SAML2_PASSWORD_PROTECTED_TRANSPORT = "urn:oasis:names:tc:SAML:2.0:ac:classes:PasswordProtectedTransport";
	private static final String SAML2_ASSERTION = "urn:oasis:names:tc:SAML:2.0:assertion";

	// 인증 요청 생성
	// AuthnRequest객체 반환
	public AuthnRequest buildAuthenticationRequest(String assertionConsumerServiceUrl, String issuerId) {

		DateTime issueInstant = new DateTime();
		AuthnRequestBuilder authRequestBuilder = new AuthnRequestBuilder();
		AuthnRequest authRequest = authRequestBuilder.buildObject(SAML2_PROTOCOL, "AuthnRequest", "samlp");
		
		// IdP가 사용자를 강제로 재인증해야 하는지 여부 설정
		authRequest.setForceAuthn(Boolean.FALSE);
		// IdP가 인증 과정 중 사용자와의 상호 작용을 끊어야 하는지 여부 설정
		authRequest.setIsPassive(Boolean.FALSE);
		// 요청이 발행된 날짜/시간 설정
		authRequest.setIssueInstant(issueInstant);
		// 요청에 대한 프로토콜 바인딩 URI를 설정
		authRequest.setProtocolBinding(SAML2_POST_BINDING);
		// 요청에 대한 응답이 전달되어야 하는 특정 Assertion 소비자 서비스의 URL을 설정
		authRequest.setAssertionConsumerServiceURL(assertionConsumerServiceUrl);
		// 요청에 대한 발행자 설정
		authRequest.setIssuer(buildIssuer(issuerId));
		// 요청의 NameID 정책 설정
		authRequest.setNameIDPolicy(buildNameIDPolicy());
		// 요청된 AuthnContext 설정
		authRequest.setRequestedAuthnContext(buildRequestedAuthnContext());
		// 요청의 고유 식별자 설정
		authRequest.setID(UUID.randomUUID().toString());
		// 요청의 SAML 버전을 설정(SAML Version 2.0)
		authRequest.setVersion(SAMLVersion.VERSION_20);

		return authRequest;
	}

	// 발행자 객체 생성
	// Issuer객체 반환
	private static Issuer buildIssuer(String issuerId) {
		IssuerBuilder issuerBuilder = new IssuerBuilder();
		Issuer issuer = issuerBuilder.buildObject();
		issuer.setValue(issuerId);
		return issuer;
	}

	// NameID 정책 객체 생성
	// NameIDPolicy객체 반환
	private static NameIDPolicy buildNameIDPolicy() {
		NameIDPolicy nameIDPolicy = new NameIDPolicyBuilder().buildObject();
		nameIDPolicy.setFormat(SAML2_NAME_ID_POLICY);
		nameIDPolicy.setAllowCreate(Boolean.TRUE);
		return nameIDPolicy;
	}

	// RequestedAuthnContext 객체 생성
	// RequestedAuthnContext 객체 반환
	private static RequestedAuthnContext buildRequestedAuthnContext() {

		// Create AuthnContextClassRef
		AuthnContextClassRefBuilder authnContextClassRefBuilder = new AuthnContextClassRefBuilder();
		AuthnContextClassRef authnContextClassRef = authnContextClassRefBuilder.buildObject(SAML2_ASSERTION,
				"AuthnContextClassRef", "saml");
		authnContextClassRef.setAuthnContextClassRef(SAML2_PASSWORD_PROTECTED_TRANSPORT);

		// Create RequestedAuthnContext
		RequestedAuthnContextBuilder requestedAuthnContextBuilder = new RequestedAuthnContextBuilder();
		RequestedAuthnContext requestedAuthnContext = requestedAuthnContextBuilder.buildObject();
		requestedAuthnContext.setComparison(AuthnContextComparisonTypeEnumeration.EXACT);
		requestedAuthnContext.getAuthnContextClassRefs().add(authnContextClassRef);

		return requestedAuthnContext;
	}
}
