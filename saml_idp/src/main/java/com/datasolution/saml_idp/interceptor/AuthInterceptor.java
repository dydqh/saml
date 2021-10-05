package com.datasolution.saml_idp.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.opensaml.common.binding.BasicSAMLMessageContext;
import org.opensaml.common.binding.decoding.SAMLMessageDecoder;
import org.opensaml.common.binding.encoding.SAMLMessageEncoder;
import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.AuthnRequest;
import org.opensaml.saml2.core.Issuer;
import org.opensaml.saml2.core.Response;
import org.opensaml.saml2.core.Status;
import org.opensaml.saml2.core.StatusCode;
import org.opensaml.saml2.metadata.Endpoint;
import org.opensaml.saml2.metadata.SingleSignOnService;
import org.opensaml.ws.security.SecurityPolicyResolver;
import org.opensaml.ws.transport.http.HttpServletResponseAdapter;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.validation.ValidatorSuite;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.saml.context.SAMLMessageContext;
import org.springframework.security.saml.key.KeyManager;
import org.springframework.web.servlet.HandlerInterceptor;

import com.datasolution.saml_idp.serviceCopy.LocalSamlPrincipalFactory;
import com.datasolution.saml_idp.serviceCopy.SamlBuilder;
import com.datasolution.saml_idp.serviceCopy.SamlMessageHandler;
import com.datasolution.saml_idp.serviceCopy.SamlPrincipal;

public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
		String uuid = (String) session.getAttribute("uuid");

		String SAMLRequest = request.getParameter("SAMLRequest");

		if (uuid == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return false;
		} else {

//			final KeyManager keyManager = null;
//			final SAMLMessageDecoder decoder = null;
//			final SecurityPolicyResolver resolver = null;
//			final List<ValidatorSuite> validatorSuites = null;
//			final SAMLMessageEncoder encoder = null;
//			final String entityId = null;
//			final String nameIdType = null;
//
//			SamlMessageHandler samlMessageHandler = new SamlMessageHandler(entityId, keyManager, decoder, encoder,
//					resolver);
//			LocalSamlPrincipalFactory samlPrincipalFactory = new LocalSamlPrincipalFactory(nameIdType);
//			SAMLMessageContext messageContext = samlMessageHandler.extractSAMLMessageContext(request, response);
//			AuthnRequest authnRequest = (AuthnRequest) messageContext.getInboundSAMLMessage();
//			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//			SamlPrincipal principal = samlPrincipalFactory.createSamlPrincipal(messageContext, authentication);
//
//			Status status = SamlBuilder.buildStatus(StatusCode.SUCCESS_URI);
//			Credential signingCredential = samlMessageHandler.resolveCredential(entityId);
//			Response authResponse = SamlBuilder.buildSAMLObject(Response.class, Response.DEFAULT_ELEMENT_NAME);
//			Issuer issuer = SamlBuilder.buildIssuer(entityId);
//			authResponse.setIssuer(issuer);
//			authResponse.setID(SamlBuilder.randomSAMLId());
//			authResponse.setIssueInstant(new DateTime());
//			authResponse.setInResponseTo(principal.getRequestID());
//			Assertion assertion = SamlBuilder.buildAssertion(principal, status, entityId);
//			SamlBuilder.signAssertion(assertion, signingCredential);
//			authResponse.getAssertions().add(assertion);
//			authResponse.setDestination(principal.getAssertionConsumerServiceUrl());
//			authResponse.setStatus(status);
//
//			Endpoint endpoint = SamlBuilder.buildSAMLObject(Endpoint.class, SingleSignOnService.DEFAULT_ELEMENT_NAME);
//			endpoint.setLocation(principal.getAssertionConsumerServiceUrl());
//			HttpServletResponseAdapter outTransport = new HttpServletResponseAdapter(response, false);
//
//			BasicSAMLMessageContext messageContext2 = new BasicSAMLMessageContext();
//			messageContext.setOutboundMessageTransport(outTransport);
//			messageContext.setPeerEntityEndpoint(endpoint);
//			messageContext.setOutboundSAMLMessage(authResponse);
//			messageContext.setOutboundSAMLMessageSigningCredential(signingCredential);
//			messageContext.setOutboundMessageIssuer(entityId);
//			messageContext.setRelayState(principal.getRelayState());
//			encoder.encode(messageContext2);
//
//			System.out.println(messageContext2);
			
			return true;
		}

	}

}
