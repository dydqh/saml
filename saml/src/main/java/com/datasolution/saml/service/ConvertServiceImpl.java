package com.datasolution.saml.service;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

@Service
public class ConvertServiceImpl implements ConvertService{

	@Override
	public Document convertStringToXMLDocument(String string) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try
        {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(string)));
            return doc;
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return null;
	}

	@Override
	public String getValueByKeyInTag(Document doc, String tag, String key) {
		Element root = doc.getDocumentElement();
		NodeList list = root.getElementsByTagName(tag);
		if(list.getLength() != 1) {
			return null;
		}
		else {
			Element element = (Element)list.item(0);
	        String value = element.getAttribute(key);
	        return value;
		}
	}
	
}
