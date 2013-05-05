package com.flyingh.dao;

import static com.flyingh.util.XmlUtils.getDocument;
import static com.flyingh.util.XmlUtils.save;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.flyingh.vo.Student;

public class StudentDao {
	public void add(Student student) throws TransformerConfigurationException,
			TransformerException, TransformerFactoryConfigurationError,
			SAXException, IOException, ParserConfigurationException {
		Document document = getDocument();

		Element studentElement = document.createElement("student");
		studentElement.setAttribute("id", String.valueOf(student.getId()));

		Element nameElement = document.createElement("name");
		Element ageElement = document.createElement("age");
		Element scoreElement = document.createElement("score");

		document.getDocumentElement().appendChild(studentElement);
		studentElement.appendChild(nameElement);
		studentElement.appendChild(ageElement);
		studentElement.appendChild(scoreElement);

		nameElement.setTextContent(student.getName());
		ageElement.setTextContent(String.valueOf(student.getAge()));
		scoreElement.setTextContent(String.valueOf(student.getScore()));

		save(document);
	}

	public void delete(int id) throws SAXException, IOException,
			ParserConfigurationException, TransformerConfigurationException,
			TransformerException, TransformerFactoryConfigurationError {
		Document document = getDocument();
		NodeList studentNodes = document.getElementsByTagName("student");
		for (int i = 0; i < studentNodes.getLength(); i++) {
			Element studentNode = (Element) studentNodes.item(i);
			if (Integer.valueOf(studentNode.getAttribute("id")) == id) {
				studentNode.getParentNode().removeChild(studentNode);
				save(document);
				return;
			}
		}
		throw new IllegalArgumentException("input id error");
	}

	public void update(Student student) throws SAXException, IOException,
			ParserConfigurationException, TransformerConfigurationException,
			TransformerException, TransformerFactoryConfigurationError {
		Document document = getDocument();

		NodeList studentNodes = document.getElementsByTagName("student");
		for (int i = 0; i < studentNodes.getLength(); i++) {
			Element studentNode = (Element) studentNodes.item(i);
			if (Integer.valueOf(studentNode.getAttribute("id")) != student
					.getId()) {
				continue;
			}
			studentNode.getElementsByTagName("name").item(0)
					.setTextContent(student.getName());
			studentNode.getElementsByTagName("age").item(0)
					.setTextContent(String.valueOf(student.getAge()));
			studentNode.getElementsByTagName("score").item(0)
					.setTextContent(String.valueOf(student.getScore()));
			save(document);
			return;
		}
		throw new IllegalArgumentException("input id error");
	}

	public Student queryById(int id) throws SAXException, IOException,
			ParserConfigurationException {
		Document document = getDocument();
		NodeList studentNodes = document.getElementsByTagName("student");
		for (int i = 0; i < studentNodes.getLength(); i++) {
			Element studentNode = (Element) studentNodes.item(i);
			if (id == Integer.valueOf(studentNode.getAttribute("id"))) {
				Student student = new Student();
				student.setId(id);
				student.setName(studentNode.getElementsByTagName("name")
						.item(0).getTextContent());
				student.setAge(Integer.valueOf(studentNode
						.getElementsByTagName("age").item(0).getTextContent()));
				student.setScore(Float
						.parseFloat(studentNode.getElementsByTagName("score")
								.item(0).getTextContent()));
				return student;
			}
		}
		throw new IllegalArgumentException("input id error");
	}
}
