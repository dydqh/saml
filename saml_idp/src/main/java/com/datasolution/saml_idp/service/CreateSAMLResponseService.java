package com.datasolution.saml_idp.service;

import java.util.HashMap;
import java.util.List;

import org.joda.time.DateTime;
import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.AttributeStatement;
import org.opensaml.saml2.core.AuthnStatement;
import org.opensaml.saml2.core.Issuer;
import org.opensaml.saml2.core.Response;
import org.opensaml.saml2.core.Status;
import org.opensaml.saml2.core.Subject;
import org.opensaml.xml.signature.Signature;

public interface CreateSAMLResponseService {

	// 응답 객체 생성
	Response createResponse(final DateTime issueDate, Issuer issuer, Status status, Assertion assertion);

	// 어설션 생성
	Assertion createAssertion(final DateTime issueDate, Subject subject, Issuer issuer, AuthnStatement authnStatement,
			AttributeStatement attributeStatement);
	
	// 생성자 생성
	Issuer createIssuer(final String issuerName);
	
	
	Subject createSubject(final String subjectId, final Integer samlAssertionDays);
	
	AuthnStatement createAuthnStatement(final DateTime issueDate);
	
	AttributeStatement createAttributeStatement(HashMap<String, List<String>> attributes);
	
	Status createStatus();
	
	Signature createSignature();
}
