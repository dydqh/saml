package com.datasolution.saml_sp;

import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.opensaml.Configuration;
import org.opensaml.DefaultBootstrap;
import org.opensaml.common.SAMLObject;
import org.opensaml.common.SAMLVersion;
import org.opensaml.common.binding.BasicSAMLMessageContext;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.binding.encoding.HTTPRedirectDeflateEncoder;
import org.opensaml.saml2.core.AuthnRequest;
import org.opensaml.saml2.core.Issuer;
import org.opensaml.saml2.core.NameIDType;
import org.opensaml.saml2.metadata.Endpoint;
import org.opensaml.saml2.metadata.SingleSignOnService;
import org.opensaml.ws.message.encoder.MessageEncodingException;
import org.opensaml.ws.transport.http.HttpServletResponseAdapter;
import org.opensaml.xml.ConfigurationException;
import org.opensaml.xml.XMLObjectBuilderFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@WebAppConfiguration
public class CreateAssertionTest {

	@SuppressWarnings("unchecked")
	private <T> T buildSAMLObject(final Class<T> objectClass, QName qName) {
		XMLObjectBuilderFactory builderFactory = Configuration.getBuilderFactory();
		return (T) builderFactory.getBuilder(qName).buildObject(qName);
	}

	private String entityId = "http://localhost:9106?test=test";
	private String acs = "/acs?test=test";
	private String ssoLocation = "http://localhost:9105/sso?test=test";
	
	@Test
	public void test() throws ConfigurationException, MessageEncodingException {
		DefaultBootstrap.bootstrap();

		ServletWebRequest servletContainer = (ServletWebRequest)RequestContextHolder.getRequestAttributes();
		HttpServletResponse response = servletContainer.getResponse();
		
		BasicSAMLMessageContext<SAMLObject, AuthnRequest, SAMLObject> context = new BasicSAMLMessageContext<>();
		HttpServletResponseAdapter transport = new HttpServletResponseAdapter(response, false);
		context.setOutboundMessageTransport(transport);
        context.setPeerEntityEndpoint(getIDPEndpoint());
		Issuer issuer = buildIssuer(entityId);
		AuthnRequest authnRequest = buildAuthnRequest(entityId + acs, SAMLConstants.SAML2_POST_BINDING_URI, issuer);
		context.setOutboundSAMLMessage(authnRequest);

		HTTPRedirectDeflateEncoder encoder = new HTTPRedirectDeflateEncoder();
		encoder.encode(context);
//		System.out.println(context.ge);
	}

	private Issuer buildIssuer(String issuingEntityName) {
		Issuer issuer = buildSAMLObject(Issuer.class, Issuer.DEFAULT_ELEMENT_NAME);
		issuer.setValue(issuingEntityName);
		issuer.setFormat(NameIDType.ENTITY);
		return issuer;
	}
	
	private AuthnRequest buildAuthnRequest(String acsUrl, String protocolBinding, Issuer issuer) {
        AuthnRequest authnRequest = buildSAMLObject(AuthnRequest.class, AuthnRequest.DEFAULT_ELEMENT_NAME);
        authnRequest.setIsPassive(true);
        authnRequest.setVersion(SAMLVersion.VERSION_20);
        authnRequest.setAssertionConsumerServiceURL(acsUrl);
        authnRequest.setProtocolBinding(protocolBinding);
        authnRequest.setIssuer(issuer);
        authnRequest.setIssueInstant(new DateTime());
        authnRequest.setID(UUID.randomUUID().toString());
        return authnRequest;
    }
	
	private Endpoint getIDPEndpoint() {
        Endpoint samlEndpoint = buildSAMLObject(Endpoint.class, SingleSignOnService.DEFAULT_ELEMENT_NAME);
        samlEndpoint.setLocation(ssoLocation);
        return samlEndpoint;
    }

}
