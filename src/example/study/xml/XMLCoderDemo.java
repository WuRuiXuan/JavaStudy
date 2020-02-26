package example.study.xml;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * XML序列化与反序列化
 */

public class XMLCoderDemo {

	public static void main(String[] args) {
		encoder();
		decoder();
	}

	public static void encoder() {
		Person p = new Person("1", "小若", 18, "女", "1234567890");
		try {
			OutputStream out = new BufferedOutputStream(new FileOutputStream(System.getProperty("user.dir") + "/person_encoder.xml"));
			XMLEncoder encoder = new XMLEncoder(out);
			encoder.writeObject(p);
			encoder.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void decoder() {
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(System.getProperty("user.dir") + "/person_encoder.xml"));
			XMLDecoder decoder = new XMLDecoder(in);
			Person p = (Person) decoder.readObject();
			decoder.close();
			System.out.println(p);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
