package com.flyingh.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.flyingh.dao.StudentDao;
import com.flyingh.vo.Student;

public class Demo {
	private StudentDao dao;
	private BufferedReader buf;

	@Before
	public void before() {
		dao = new StudentDao();
		buf = new BufferedReader(new InputStreamReader(System.in));
	}

	@Test
	public void test() throws IOException, TransformerConfigurationException,
			TransformerException, TransformerFactoryConfigurationError,
			SAXException, ParserConfigurationException {
		while (true) {
			chooseAction();
		}
	}

	private void chooseAction() throws IOException,
			TransformerConfigurationException, TransformerException,
			TransformerFactoryConfigurationError, SAXException,
			ParserConfigurationException {
		System.out
				.println("choose:\r\na(add)\tb(delete)\tc(update)\td(query)\tq(exit)");
		String action = buf.readLine();
		switch (action) {
		case "a":
			doAdd();
			break;
		case "b":
			doDelete();
			break;
		case "c":
			doUpdate();
			break;
		case "d":
			doQuery();
			break;
		case "q":
			doExit();
			break;
		default:
			throw new IllegalArgumentException("input error!");
		}

	}

	private void doExit() {
		System.exit(0);
	}

	private void doQuery() throws IOException, SAXException,
			ParserConfigurationException {
		System.out.println("input id:");
		int id = Integer.valueOf(buf.readLine());
		Student student = dao.queryById(id);
		System.out.println("query success");
		System.out.println(student);
	}

	private void doUpdate() throws NumberFormatException, IOException,
			SAXException, ParserConfigurationException,
			TransformerConfigurationException, TransformerException,
			TransformerFactoryConfigurationError {
		System.out.println("input id:");
		int id = Integer.valueOf(buf.readLine());
		System.out.println("input new name:");
		String name = buf.readLine();
		System.out.println("input new age:");
		int age = Integer.valueOf(buf.readLine());
		System.out.println("input new score:");
		float score = Float.valueOf(buf.readLine());
		dao.update(new Student(id, name, age, score));
		System.out.println("update success");
	}

	private void doDelete() throws NumberFormatException, IOException,
			TransformerConfigurationException, SAXException,
			ParserConfigurationException, TransformerException,
			TransformerFactoryConfigurationError {
		System.out.println("input id:");
		Integer id = Integer.valueOf(buf.readLine());
		dao.delete(id);
		System.out.println("delete success");
	}

	private void doAdd() throws IOException, TransformerConfigurationException,
			TransformerException, TransformerFactoryConfigurationError,
			SAXException, ParserConfigurationException {
		System.out.println("input id:");
		int id = Integer.valueOf(buf.readLine());
		System.out.println("input name:");
		String name = buf.readLine();
		System.out.println("input age:");
		int age = Integer.valueOf(buf.readLine());
		System.out.println("input score:");
		float score = Float.valueOf(buf.readLine());
		dao.add(new Student(id, name, age, score));
		System.out.println("add success");
	}
}
