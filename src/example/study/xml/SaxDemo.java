package example.study.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX解析
 * 1. Simple API for XML的缩写，基于事件驱动
 * 2. 一边读取文档一边解析，更快速、更轻量，节省内存，顺序读取，灵活性较差
 * 3. 适用于在性能要求较高的设备上使用（在android开发中常用）
 */

public class SaxDemo {

	public static void main(String[] args) {
		parseSaxXML();
	}

	public static void parseSaxXML() {
		// 创建解析工厂
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			// 生成解析器
			SAXParser parser = factory.newSAXParser();
			InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("example/study/xml/person.xml");
			PersonHandler dh = new PersonHandler();
			parser.parse(in, dh);
			for (Person person : dh.getPersons()) {
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
	
	// 数据处理器
	static class PersonHandler extends DefaultHandler {
		private List<Person> persons;
		private Person p = null;
		private String tag = null;
		
		public List<Person> getPersons() {
			return persons;
		}

		public void setPersons(List<Person> persons) {
			this.persons = persons;
		}

		@Override
		public void startDocument() throws SAXException {
			super.startDocument();
			persons = new ArrayList<Person>();
			System.out.println("开始解析文档");
		}
		
		// 开始标签 localName-不带前缀的标签名（标签前缀如：<a:person>） qName-完整的标签名
		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes)
				throws SAXException {
			super.startElement(uri, localName, qName, attributes);
//			System.out.println(localName); // 不明白为啥为空
			if (qName.equals("person")) {
				p = new Person();
				p.setId(attributes.getValue("id"));
			}
			tag = qName;
		}
		
		// 处理文本内容（包括空格）
		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			super.characters(ch, start, length);
			if (tag != null) {
				if (tag.equals("name")) {
					p.setName(new String(ch, start, length));
				}
				else if (tag.equals("age")) {
					p.setAge(Integer.parseInt(new String(ch, start, length)));
				}
				else if (tag.equals("sex")) {
					p.setSex(new String(ch, start, length));
				}
				else if (tag.equals("tel")) {
					p.setTel(new String(ch, start, length));
				}
			}
		}
		
		// 结束标签
		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			super.endElement(uri, localName, qName);
			if (qName.equals("person")) {
				persons.add(p);
			}
			tag = null;
		}
		
		@Override
		public void endDocument() throws SAXException {
			super.endDocument();
			System.out.println("文档解析完毕");
		}
	}
}
