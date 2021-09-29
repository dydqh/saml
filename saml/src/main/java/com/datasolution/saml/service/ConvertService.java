package com.datasolution.saml.service;

import org.w3c.dom.Document;

public interface ConvertService {
	
	Document convertStringToXMLDocument(String xmlString);

	String getValueByKeyInTag(Document doc, String tag, String key);
	
	String getTextByTag(Document doc, String tag);
}
