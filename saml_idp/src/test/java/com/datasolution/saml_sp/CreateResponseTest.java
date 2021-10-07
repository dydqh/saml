package com.datasolution.saml_sp;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

import javax.xml.namespace.QName;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.opensaml.Configuration;
import org.opensaml.DefaultBootstrap;
import org.opensaml.common.SAMLVersion;
import org.opensaml.common.binding.BasicSAMLMessageContext;
import org.opensaml.saml2.core.Response;
import org.opensaml.saml2.core.impl.ResponseBuilder;
import org.opensaml.saml2.core.impl.ResponseMarshaller;
import org.opensaml.xml.XMLObjectBuilderFactory;
import org.opensaml.xml.util.XMLHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.w3c.dom.Element;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@WebAppConfiguration
public class CreateResponseTest {
	
	private static final XMLObjectBuilderFactory builderFactory = Configuration.getBuilderFactory();

    @SuppressWarnings("unchecked")
    static <T> T buildSAMLObject(final Class<T> objectClass, QName qName) {
        return (T) builderFactory.getBuilder(qName).buildObject(qName);
    }
    
	@Test
	public void test() throws Exception {
		DefaultBootstrap.bootstrap();
		
		ResponseBuilder responseBuilder = new ResponseBuilder();
		Response response = responseBuilder.buildObject();
		
		response.setID(UUID.randomUUID().toString());
		response.setIssueInstant(null);
		response.setVersion(SAMLVersion.VERSION_20);
		response.setIssuer(null);
		response.setStatus(null);
		response.getAssertions().add(null);
		
		ResponseMarshaller marshaller = new ResponseMarshaller();
		Element element = marshaller.marshall(response);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		XMLHelper.writeNode(element, baos);
		String responseStr = new String(baos.toByteArray());
		
		System.out.println(responseStr);
	}
	
}
