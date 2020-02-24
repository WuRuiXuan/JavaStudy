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

/**
 * 对象流
 */

public class ObjectStreamDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		objectOut();
		objectIn();
	}

	public static void objectOut() {
		Cat cat = new Cat("咪咪", 3);
		try {
			OutputStream out = new FileOutputStream(System.getProperty("user.dir") + "/cat.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(cat); // 存多个对象用数组
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void objectIn() {
		try {
			InputStream in = new FileInputStream(System.getProperty("user.dir") + "/cat.txt");
			ObjectInputStream ois = new ObjectInputStream(in);
			Cat cat = (Cat) ois.readObject();
			System.out.println(cat.toString());
			ois.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static class Cat implements Serializable {
		private static final long serialVersionUID = 1L;
		private String name;
		private int age;
		private transient String color;// 表示该属性不会被序列化

		public Cat(String name, int age) {
			super();
			this.name = name;
			this.age = age;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "Cat: " + name + " " + age + "岁";
		}

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

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}
	}
}