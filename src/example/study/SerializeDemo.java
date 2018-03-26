package example.study;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class SerializeDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		objectOut();
		objectIn();
	}
	
	public static void objectOut() {
		Cat2 cat = new Cat2("咪咪", 3);
		try {
			OutputStream out = new FileOutputStream("/Users/wuruixuan/Desktop/cat.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(cat); // 存多个对象用数组
			oos.close();
		}
		catch (FileNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void objectIn() {
		try {
			InputStream in = new FileInputStream("/Users/wuruixuan/Desktop/cat.txt");
			ObjectInputStream ois = new ObjectInputStream(in);
			Cat2 cat = (Cat2)ois.readObject();
			System.out.println(cat);
			ois.close();
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
}

class Cat2 implements Serializable {
	private String name;
	private int age;
	private transient String color;// 表示该属性不会被序列化
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Cat2(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	
}
