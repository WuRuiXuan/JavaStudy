package example.study.xml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * DOM4J解析
 * 1. 开源项目，下载地址：https://dom4j.github.io/
 * 2. 性能最好，功能强大，易用性高
 * 3. 普遍用于服务器端开发
 */

public class Dom4jDemo {

	public static void main(String[] args) {
		parseDom4jXML();
	}

	public static void parseDom4jXML() {
		ArrayList<Person> list = new ArrayList<>();
		SAXReader reader = new SAXReader();
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("example/study/xml/person.xml");
		try {
			Document doc = reader.read(in);
			Element root = doc.getRootElement();
			Iterator<Element> iterator = root.elementIterator();
			while (iterator.hasNext()) {
				Element e = iterator.next();
				Person p = new Person();
				p.setId(e.attributeValue("id"));
				Iterator<Element> i = e.elementIterator();
				while (i.hasNext()) {
					Element el = i.next();
					String name = el.getName();
					if (name.equals("name")) {
						p.setName(el.getText());
					}
					else if (name.equals("age")) {
						p.setAge(Integer.valueOf(el.getText()));
					}
					else if (name.equals("sex")) {
						p.setSex(el.getText());
					}
					else if (name.equals("tel")) {
						p.setTel(el.getText());
					}
				}
				list.add(p);
			}
			for (Person person : list) {
				System.out.println(person);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
