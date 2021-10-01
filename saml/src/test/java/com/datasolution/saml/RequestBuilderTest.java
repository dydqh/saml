package com.datasolution.saml;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterOutputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.opensaml.saml2.core.AuthnRequest;
import org.opensaml.xml.io.Marshaller;
import org.opensaml.xml.util.Base64;
import org.opensaml.xml.util.XMLHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.datasolution.saml.service.AuthNRequestBuilder;
import com.datasolution.saml.service.LoginService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" // DispatcherServlet(web.xml에 대한 정보가필요)
})
@WebAppConfiguration
@PropertySource("classpath:properties/application.properties")
public class RequestBuilderTest {

	@Value("${IDP_SSO_URL}")
	private String idpAppURL;

	@Value("${RELAYSTATE_BASE_URL}")
	private String relayState;

	@Value("${ACS_URL}")
	private String assertionConsumerServiceUrl;

	@Value("${IDP_ISSUER_ID}")
	private String issuerId;

	@Autowired
	private LoginService loginService;

	@Test
	public void test() throws Exception {
		String redirectUrl = loginService.getAuthNRedirectUrl(idpAppURL, relayState, assertionConsumerServiceUrl,
				issuerId);
		System.out.println(redirectUrl);

		AuthNRequestBuilder authNRequestBuilder = new AuthNRequestBuilder();
		AuthnRequest authRequest = authNRequestBuilder.buildAuthenticationRequest(assertionConsumerServiceUrl,
				issuerId);
		String samlRequest = generateSAMLRequest(authRequest);
		System.out.println("samlRequest : " + samlRequest);

		String decodedTarget = URLDecoder.decode(samlRequest, "UTF-8");
		System.out.println("decodedTarget : " + decodedTarget);

		byte[] target = Base64.decode(decodedTarget);
		System.out.println("target : " + target);

	}

	private String generateSAMLRequest(AuthnRequest authRequest) throws Exception {

		Marshaller marshaller = org.opensaml.Configuration.getMarshallerFactory().getMarshaller(authRequest);
		org.w3c.dom.Element authDOM = marshaller.marshall(authRequest);
		StringWriter rspWrt = new StringWriter();
		XMLHelper.writeNode(authDOM, rspWrt);
		String messageXML = rspWrt.toString();
		System.out.println(messageXML);

		Deflater deflater = new Deflater(Deflater.DEFLATED, true);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream, deflater);
		deflaterOutputStream.write(messageXML.getBytes());
		deflaterOutputStream.close();
		byte[] target = byteArrayOutputStream.toByteArray();
		System.out.println("target : " + target);
		String encodedTarget = Base64.encodeBytes(target, Base64.DONT_BREAK_LINES);
		System.out.println("EncodedTarget : " + encodedTarget);
		return URLEncoder.encode(encodedTarget, "UTF-8");

	}

}
