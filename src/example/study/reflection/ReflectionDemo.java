package example.study.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionDemo {

	public static void main(String[] args) {
		// 获取一个Class类对象的方式
		Cat cat = new Cat("mimi", 2);

		// 获取该对象的类信息（3种方法）
		Class catClass = cat.getClass();
		Class catClass2 = Cat.class;
		try {
			Class catClass3 = Class.forName("example.study.reflection.Cat");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// 通过类信息创建对象，使用无参构造方法进行实例化（该类必须保留无参构造方法，否则会报错）
		try {
			Cat cat2 = (Cat) catClass2.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

		// 获取构造方法
		Constructor[] cs = catClass2.getConstructors();
		for (int i = 0; i < cs.length; i++) {
			System.out.println("getConstructors: " + cs[i].getName());
		}
		
		System.out.println();
		
		try {
			Constructor c = catClass2.getConstructor(String.class, int.class);
			Cat cat3 = (Cat) c.newInstance("miaomiao", 3);
			System.out.println(cat3);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		System.out.println();
		
		// 获取包名
		String packageName = catClass2.getPackage().getName();
		System.out.println("packageName: " + packageName);
		
		System.out.println();
		
		// 获取所有可用（公共）的方法
		Method[] methods = catClass2.getMethods();
		for (int i = 0; i < methods.length; i++) {
			System.out.println("getMethods: " + methods[i].getName());
		}
		
		System.out.println();
		
		// 获取所有可用（公共）的属性
		Field[] fields = catClass2.getFields();
		for (int i = 0; i < fields.length; i++) {
			System.out.println("getFields: " + fields[i].getName());
		}
		
		System.out.println();
		
		// 获取类中定义的所有方法（包括私有方法）
		Method[] dMethods = catClass2.getDeclaredMethods();
		for (int i = 0; i < dMethods.length; i++) {
			System.out.println("getDeclaredMethods: " + dMethods[i].getName());
		}
		
		System.out.println();
		
		// 获取类中定义的所有属性（包括私有属性）
		Field[] dFields = catClass2.getDeclaredFields();
		for (int i = 0; i < dFields.length; i++) {
			System.out.println("getDeclaredFields: " + dFields[i].getName());
			System.out.println(Modifier.toString(dFields[i].getModifiers()));
			System.out.println(dFields[i].getType().getName());
		}
		
		System.out.println();
		
		// 获取属性值
		try {
			dFields[0].setAccessible(true); // 运行时忽略访问修饰符的限制
			String name = (String) dFields[0].get(cat);
			System.out.println("cat name: " + name);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		// 获取方法
		try {
			Method soundMethod = catClass2.getDeclaredMethod("sound", String.class);
			soundMethod.setAccessible(true);
			soundMethod.invoke(cat, "miaow...");
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
