package com.datasolution.saml_idp.service;

import java.util.List;

import javax.xml.namespace.QName;

import org.joda.time.DateTime;
import org.opensaml.saml2.core.*;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.signature.SignableXMLObject;

public interface CreateService {

	<T> T buildSAMLObject(final Class<T> objectClass, QName qName);
	
	Issuer buildIssuer(String issuingEntityName);

	Subject buildSubject(String subjectNameId, String subjectNameIdType, String recipient, String inResponseTo);

	Status buildStatus(String value);

	Status buildStatus(String value, String subStatus, String message);

	Assertion buildAssertion(SamlPrincipal principal, Status status, String entityId);

	void signAssertion(SignableXMLObject signableXMLObject, Credential signingCredential) throws Exception;

	String randomSAMLId();
	
	AuthnStatement buildAuthnStatement(DateTime authnInstant, String entityID);
	
	AttributeStatement buildAttributeStatement(List<SamlAttribute> attributes);
	
	Attribute buildAttribute(String name, List<String> values);
	
}
