package com.datasolution.saml.service;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.net.URLEncoder;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

import org.opensaml.DefaultBootstrap;
import org.opensaml.saml2.core.AuthnRequest;
import org.opensaml.xml.io.Marshaller;
import org.opensaml.xml.util.Base64;
import org.opensaml.xml.util.XMLHelper;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

	{
		try {
			DefaultBootstrap.bootstrap();
		} catch (Exception e) {
			log.error("SAML initialize가 불가능합니다.");
			e.printStackTrace();
		}
	}

	@Override
	public String getAuthNRedirectUrl(String idpAppURL, String relayState, String assertionConsumerServiceUrl,
			String issuerId) {
		String url = null;

		try {
			AuthNRequestBuilder authNRequestBuilder = new AuthNRequestBuilder();
			AuthnRequest authRequest = authNRequestBuilder.buildAuthenticationRequest(assertionConsumerServiceUrl,
					issuerId);
			String samlRequest = generateSAMLRequest(authRequest);

			// Prepare final Url
			url = idpAppURL + "?SAMLRequest=" + samlRequest + "&RelayState=" + URLEncoder.encode(relayState, "UTF-8");

		} catch (Exception ex) {
			log.error("Exception while creating AuthN request - " + ex.getMessage(), ex);
			throw new RuntimeException("Unable to generate redirect Url");
		}

		log.debug("redirect url is = " + url);
		return url;
	}

	// Converts AuthN object to xml, compresses it, base64 encode it and url encode
	// it
	private String generateSAMLRequest(AuthnRequest authRequest) throws Exception {

		Marshaller marshaller = org.opensaml.Configuration.getMarshallerFactory().getMarshaller(authRequest);
		org.w3c.dom.Element authDOM = marshaller.marshall(authRequest);
		StringWriter rspWrt = new StringWriter();
		XMLHelper.writeNode(authDOM, rspWrt);
		String messageXML = rspWrt.toString();

		Deflater deflater = new Deflater(Deflater.DEFLATED, true);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream, deflater);
		deflaterOutputStream.write(messageXML.getBytes());
		deflaterOutputStream.close();
		String samlRequest = Base64.encodeBytes(byteArrayOutputStream.toByteArray(), Base64.DONT_BREAK_LINES);
		return URLEncoder.encode(samlRequest, "UTF-8");

	}

}