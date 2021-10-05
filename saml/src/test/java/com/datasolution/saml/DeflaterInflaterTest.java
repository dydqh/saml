package com.datasolution.saml;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;

import javax.xml.bind.DatatypeConverter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" // DispatcherServlet(web.xml에 대한 정보가필요)
})
@WebAppConfiguration
public class DeflaterInflaterTest {

	@Test
	public void test() throws Exception {
		String messageXML = "TESTTESTTESTTESTTESTTESTTESTTEST";

		Deflater deflater = new Deflater(Deflater.DEFLATED, true);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream, deflater);
		deflaterOutputStream.write(messageXML.getBytes());
		deflaterOutputStream.close();
		byte[] target = byteArrayOutputStream.toByteArray();

		System.out.println("target : " + target);

		byte[] output = new byte[100];
		
		byte[] data = messageXML.getBytes("UTF-8");
		Deflater compresser = new Deflater();
		compresser.setInput(target);
		compresser.finish();
		int bytesAfterdeflate = compresser.deflate(output);
		System.out.println("Compressed byte number:" + bytesAfterdeflate);
		System.out.println(bytesAfterdeflate);
		Inflater decompresser = new Inflater();
		System.out.println("output : " + output);
		decompresser.setInput(output, 0, bytesAfterdeflate);
		byte[] result = new byte[100];
		int resultLength = decompresser.inflate(result);
		decompresser.end();
		String outStr = new String(result, 0, resultLength, "UTF-8");
		System.out.println("Decompressed data: " + outStr);
	}

}
