package example.study.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 * JDOM解析
 * 1. 开源项目，下载地址：http://www.jdom.org/downloads/
 * 2. 比DOM更快，简化了API，但也限制了灵活性
 * 3. 大量使用了Collections类
 */

public class JDomDemo {

	public static void main(String[] args) {
		parseJDomXML();
	}
	
	public static void parseJDomXML() {
		ArrayList<Person> list = new ArrayList<>();
		SAXBuilder builder = new SAXBuilder();
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("example/study/xml/person.xml");
		try {
			// 注意是org.jdom下的Document、Element
			Document doc = builder.build(in);
			// 获取根节点
			Element root = doc.getRootElement();
			List<Element> es = root.getChildren();
			for (int i = 0; i < es.size(); i++) {
				Element e = es.get(i);
				Person p = new Person();
				p.setId(e.getAttributeValue("id"));
				List<Element> elList = e.getChildren();
				for (int j = 0; j < elList.size(); j++) {
					Element el = elList.get(j);
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
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
