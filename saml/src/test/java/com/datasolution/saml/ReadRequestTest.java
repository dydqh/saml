package com.datasolution.saml;

import java.io.ByteArrayOutputStream;
import java.net.URLDecoder;
import java.util.zip.Inflater;
import java.util.zip.InflaterOutputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.owasp.esapi.codecs.Base64;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" // DispatcherServlet(web.xml에 대한 정보가필요)
})
@WebAppConfiguration
public class ReadRequestTest {

	String samlRequest = "nZNRb9owEMe%2FSuT3kJBCChahYlTVkLougnQPvBnnUqwldupzCP32s5O0Y9KGqr3Ykn2%2B%2F93vf17c%0AnavSO4FGoWRCxqOQeCC5yoV8Schz9uDPyN1ygawqa7pqzFFu4bUBNJ59J5F2FwlptKSKoUAqWQVI%0ADae71bdHGo1CWmtlFFcl8VaIoI0VWiuJTQV6B%2FokODxvHxNyNKZGGgSlehFyxFVVM%2Fnm9gBR6V40%0AEHkdqJ%2BGEe9BaQ5dQQkpWIlAvM19Qg68KKJ5PvMnE7DLdHrrzyM29w%2Bcsxs2L%2BJJbDvcYMoQxQl%2B%0Av0VsYCPRMGkSEoXR2B%2BHfjjOwhkNYzqdjWa3kz3x0qGZL0L2iK51fuiDkH7NstRPv%2B8y4v14R20D%0ASA82op26vkAaXU%2FM3kGSpcNmqbVtO3JgOmChYtHN69Qcx6tWP6X7fTyPF8Gl1CBc0yebe3OfqlLw%0At0v9z3talqpda2DGwjS6gc6aipnrCdyJyP2iC6W1g4IGpCHBR2nDoEHeuWxHxsDZ%2FFeNazdMWqDD%0ADmfGzQCeXmZel5bqFooLhU%2BbcDWMU%2B5S22M3dK3SuRsi4LazTDOJtdKmd%2Bev9SwH5%2F4BZLj%2B83Mu%0AfwE%3D";

	@Test
	public void test() throws Exception {
		String encodedTarget = URLDecoder.decode(samlRequest, "UTF-8");
		
		byte[] target = Base64.decode(encodedTarget);
		System.out.println("target : " + target);
	}

}
