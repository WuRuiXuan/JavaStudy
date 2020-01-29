package example.study;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
//import java.io.Writer;

/**
 * 字符串流
 */

public class StringStreamDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		stringRead();
		stringWrite();
	}
	
	public static void stringRead() {
		String info = "Java从入门到放弃";
		Reader reader = new StringReader(info);
		char[] cs = new char[1024];
		int len = -1;
		StringBuffer bf = new StringBuffer();
		try {
			while ((len = reader.read(cs)) != -1) {
				bf.append(new String(cs, 0, len));
			}
			reader.close();
			String text = bf.toString();
			System.out.println("reader的内容是：" + text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void stringWrite() {
		StringWriter writer = new StringWriter();
		try {
			writer.write("Hello World!");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("writer的内容是：" + writer.toString());
		// 关闭后仍然可以使用
		writer.write("Java从入门到放弃");
		System.out.println("writer的内容是：" + writer.toString());
	}
}
