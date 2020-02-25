package example.study.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * DOM解析
 * 1. 整个文档载入内存（占内存）
 * 2. 灵活性好，API简单
 * 3. 以树型结构存储
 * 4. 在服务器端开发中常用
 */

public class DomDemo {

	public static void main(String[] args) {
		parseDomXML();
	}

	public static void parseDomXML() {
		// 创建一个文档解析工厂
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			// 创建一个文档解析器
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("example/study/xml/person.xml");
			// 把XML文件的输入流放入解析器中
			Document doc = builder.parse(in);
			// 从解析器中获取数据
			Person p = null;
			List<Person> list = new ArrayList<>();
			NodeList personList = doc.getElementsByTagName("person");
			for (int j = 0; j < personList.getLength(); j++) {
				Node personNode = personList.item(j);
				p = new Person();
				p.setId(personNode.getAttributes().getNamedItem("id").getNodeValue());
				NodeList childList = personNode.getChildNodes();
				for (int k = 0; k < childList.getLength(); k++) {
					Node childNode = childList.item(k);
					String name = childNode.getNodeName();
					if (name.equals("name")) {
						p.setName(childNode.getFirstChild().getNodeValue());
					}
					else if (name.equals("age")) {
						p.setAge(Integer.valueOf(childNode.getFirstChild().getNodeValue()));
					}
					else if (name.equals("sex")) {
						p.setSex(childNode.getFirstChild().getNodeValue());
					}
					else if (name.equals("tel")) {
						p.setTel(childNode.getFirstChild().getNodeValue());
					}
				}
				list.add(p);
			}
			for (Person person : list) {
				System.out.println(person);
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
