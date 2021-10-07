package com.datasolution.saml_sp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@WebAppConfiguration
public class CreateAssertionTest2 {

	String SAMLRequest = "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz48c2FtbHA6QXV0aG5SZXF1ZXN0IHhtbG5zOnNhbWxwPSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6cHJvdG9jb2wiIEFzc2VydGlvbkNvbnN1bWVyU2VydmljZVVSTD0iaHR0cHM6Ly9sb2dpbi5jb21wYW55LmNvbS9zc29yZXF1ZXN0L2lkcC9va3RhIiBGb3JjZUF1dGhuPSJmYWxzZSIgSUQ9IjVjYTk2ZmFjLTVjY2YtNDJjMC04NjBhLTA3Y2JiNzE3NTZkMSIgSXNQYXNzaXZlPSJmYWxzZSIgSXNzdWVJbnN0YW50PSIyMDIxLTEwLTA1VDA2OjA4OjA4LjM3MVoiIFByb3RvY29sQmluZGluZz0idXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6Mi4wOmJpbmRpbmdzOkhUVFAtUE9TVCIgVmVyc2lvbj0iMi4wIj48c2FtbDI6SXNzdWVyIHhtbG5zOnNhbWwyPSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6YXNzZXJ0aW9uIj5odHRwOi8vd3d3Lm9rdGEuY29tLzBvYTIzcTV0aDFBd3JOUFpaNjk2PC9zYW1sMjpJc3N1ZXI+PHNhbWwycDpOYW1lSURQb2xpY3kgeG1sbnM6c2FtbDJwPSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6cHJvdG9jb2wiIEFsbG93Q3JlYXRlPSJ0cnVlIiBGb3JtYXQ9InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDpuYW1laWQtZm9ybWF0OnBlcnNpc3RlbnQiLz48c2FtbDJwOlJlcXVlc3RlZEF1dGhuQ29udGV4dCB4bWxuczpzYW1sMnA9InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDpwcm90b2NvbCIgQ29tcGFyaXNvbj0iZXhhY3QiPjxzYW1sOkF1dGhuQ29udGV4dENsYXNzUmVmIHhtbG5zOnNhbWw9InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDphc3NlcnRpb24iPnVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDphYzpjbGFzc2VzOlBhc3N3b3JkUHJvdGVjdGVkVHJhbnNwb3J0PC9zYW1sOkF1dGhuQ29udGV4dENsYXNzUmVmPjwvc2FtbDJwOlJlcXVlc3RlZEF1dGhuQ29udGV4dD48L3NhbWxwOkF1dGhuUmVxdWVzdD4=";

	@Test
	public void test() {
//
//		HttpServletRequest request = mock(HttpServletRequest.class);
//		HttpServletResponse response = mock(HttpServletResponse.class);
//
//		SAMLMessageContext messageContext = new SAMLMessageContext();
//		HttpServletRequestAdapter inTransport = new HttpServletRequestAdapter(request);
//		HttpServletResponseAdapter outTransport = new HttpServletResponseAdapter(response, request.isSecure());
//		request.setAttribute(org.springframework.security.saml.SAMLConstants.LOCAL_CONTEXT_PATH,
//				request.getContextPath());
//		messageContext.setInboundMessageTransport(inTransport);
//		messageContext.setOutboundMessageTransport(outTransport);
////        SecurityPolicyResolver resolver = new SecurityPolicyResolver();
////        messageContext.setSecurityPolicyResolver(resolver);
////        decoder.decode(messageContext);
//
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//		AuthnRequest authnRequest = (AuthnRequest) messageContext.getInboundSAMLMessage();
//		List<SamlAttribute> attributes = createAttributes(authentication);
//		return SamlPrincipal.builder(authentication.getName(), nameIdType, attributes)
//				.serviceProviderEntityID(authnRequest.getIssuer().getValue()).requestID(authnRequest.getID())
//				.assertionConsumerServiceUrl(authnRequest.getAssertionConsumerServiceURL())
//				.relayState(messageContext.getRelayState()).build();
	}
//
//	protected abstract List<SamlAttribute> createAttributes(Authentication authentication);

}
