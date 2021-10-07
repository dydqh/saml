package com.datasolution.saml_sp;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.opensaml.DefaultBootstrap;
import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.AttributeStatement;
import org.opensaml.saml2.core.AuthnStatement;
import org.opensaml.saml2.core.Issuer;
import org.opensaml.saml2.core.Response;
import org.opensaml.saml2.core.Status;
import org.opensaml.saml2.core.Subject;
import org.opensaml.saml2.core.impl.ResponseMarshaller;
import org.opensaml.xml.signature.Signature;
import org.opensaml.xml.signature.Signer;
import org.opensaml.xml.util.XMLHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.w3c.dom.Element;

import com.datasolution.saml_idp.service.CreateSAMLResponseService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@WebAppConfiguration
public class CreateResponseTest2 {

	@Autowired
	private CreateSAMLResponseService createSAMLResponseService;

	private String issuer;
	private String subjectId;
	private Integer samlAssertionDays;
	private HashMap<String, List<String>> attributes;
	private DateTime authenticationTime;

	@Test
	public void test() throws Exception {

		DefaultBootstrap.bootstrap();

		Signature signature = createSAMLResponseService.createSignature();
		Status status = createSAMLResponseService.createStatus();
		Issuer responseIssuer = null;
		Issuer assertionIssuer = null;
		Subject subject = null;
		AttributeStatement attributeStatement = null;

		if (issuer != null) {
			responseIssuer = createSAMLResponseService.createIssuer(issuer);
			assertionIssuer = createSAMLResponseService.createIssuer(issuer);
		}

		if (subjectId != null) {
			subject = createSAMLResponseService.createSubject(subjectId, samlAssertionDays);
		}

		if (attributes != null && attributes.size() != 0) {
			attributeStatement = createSAMLResponseService.createAttributeStatement(attributes);
		}

		AuthnStatement authnStatement = createSAMLResponseService.createAuthnStatement(authenticationTime);

		Assertion assertion = createSAMLResponseService.createAssertion(new DateTime(), subject, assertionIssuer,
				authnStatement, attributeStatement);

		Response response = createSAMLResponseService.createResponse(new DateTime(), responseIssuer, status, assertion);
		response.setSignature(signature);
		
		//Destination 설정
		response.setDestination("Test");

		ResponseMarshaller marshaller = new ResponseMarshaller();
		Element element = marshaller.marshall(response);

		if (signature != null) {
			Signer.signObject(signature);
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		XMLHelper.writeNode(element, baos);

		String responseStr = new String(baos.toByteArray());

		System.out.println(responseStr);
	}

}
