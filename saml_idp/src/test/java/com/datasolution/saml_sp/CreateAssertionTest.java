package com.datasolution.saml_sp;

import javax.xml.namespace.QName;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.opensaml.Configuration;
import org.opensaml.DefaultBootstrap;
import org.opensaml.saml2.core.Issuer;
import org.opensaml.saml2.core.NameIDType;
import org.opensaml.xml.ConfigurationException;
import org.opensaml.xml.XMLObjectBuilderFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@WebAppConfiguration
public class CreateAssertionTest {

	
	
	static XMLObjectBuilderFactory builderFactory = Configuration.getBuilderFactory();
	
	@SuppressWarnings("unchecked")
	static <T> T buildSAMLObject(final Class<T> objectClass, QName qName) {
		return (T) builderFactory.getBuilder(qName).buildObject(qName);
	}

	static Issuer buildIssuer(String issuingEntityName) {
//        Issuer issuer = buildSAMLObject(Issuer.class, Issuer.DEFAULT_ELEMENT_NAME);
		Issuer issuer = (Issuer) builderFactory.getBuilder(Issuer.DEFAULT_ELEMENT_NAME)
				.buildObject(Issuer.DEFAULT_ELEMENT_NAME);
		System.out.println("1");
		issuer.setValue(issuingEntityName);
		System.out.println("2");
		issuer.setFormat(NameIDType.ENTITY);
		System.out.println("3");
		return issuer;
	}

	@Test
	public void test() throws ConfigurationException {
		DefaultBootstrap.bootstrap();
		
		System.out.println("4");
		System.out.println(Issuer.DEFAULT_ELEMENT_NAME);
		System.out.println("5");
		System.out.println(Issuer.DEFAULT_ELEMENT_NAME);
		System.out.println("6");
		System.out.println(builderFactory.toString());
		System.out.println(Issuer.DEFAULT_ELEMENT_NAME == null);
		System.out.println(builderFactory.getBuilder(Issuer.DEFAULT_ELEMENT_NAME));
		System.out.println(builderFactory.getBuilders());
		System.out.println("7");
		
//		Issuer issuer = (Issuer) builderFactory.getBuilder(Issuer.DEFAULT_ELEMENT_NAME)
//				.buildObject(Issuer.DEFAULT_ELEMENT_NAME);
		System.out.println("7");
		Issuer issuer = CreateAssertionTest.buildIssuer("AA");
		System.out.println("8");
	}

}
