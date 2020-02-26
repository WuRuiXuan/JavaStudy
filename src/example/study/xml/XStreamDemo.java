package example.study.xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Xpp3Driver;

/**
 * XStream序列化/反序列化工具
 */

public class XStreamDemo {

	public static void main(String[] args) {
		Person p = new Person("1", "小若", 18, "女", "1234567890");
		XStream xs = new XStream(new Xpp3Driver());
		// 命名根标签
		xs.alias("person", Person.class);
		xs.useAttributeFor("id", String.class);
		String xml = xs.toXML(p);
		System.out.println(xml);
		
		Person p2 = (Person) xs.fromXML(xml);
		System.out.println(p2);
	}

}
