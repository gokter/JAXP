package com.flyingh.util;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XmlUtils {
	private static final String PATH = "src/students.xml";

	public static Document getDocument() throws SAXException, IOException,
			ParserConfigurationException {
		return DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse(PATH);
	}

	public static void save(Document document)
			throws TransformerConfigurationException, TransformerException,
			TransformerFactoryConfigurationError, SAXException, IOException,
			ParserConfigurationException {
		TransformerFactory.newInstance().newTransformer()
				.transform(new DOMSource(document), new StreamResult(PATH));
	}
}
