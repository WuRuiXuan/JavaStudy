package example.study;

import java.util.Collection;
import java.util.Vector;

/**
 * 泛型
 */

public class GenericDemo {

	public static void main(String[] args) {
		// 参数化类型与原始类型的兼容性（定义数组不能使用泛型）
		Collection<String> c = new Vector<>();
		Collection c2 = new Vector<String>();
		
		Person<Integer> p = new Person<>();
		p.setName("小花");
		p.setAge(18);
		
		Person p2 = new Person();
		// 擦除泛型会当作Object类型使用
		p2.setAge(18);
		
		Person<Integer> p3 = new Person<>("小若", 18);
		Person<Float> p4 = new Person<>("小花", 18.5f);
//		p3 = p4;
		print(p3);
		print(p4);
		
		System.out.println();
		
		String[] array = {"one", "two"};
		String[] newArray = func(array, 0, 1);
		for (int i = 0; i < newArray.length; i++) {
			System.out.println("index" + i + ": " + newArray[i]);
		}
	}
	
	// 通配符
	public static void print(Person<?> p) {
		System.out.println(p.getName() + " " + p.getAge());
		// 只能获取，不能修改
//		p.setAge(20);
		p.setName("小明");
	}
	
	// 泛型方法
	public static <T> T[] func(T[] array, int i, int j) {
		T temp = array[i];
		array[i] = array[j];
		array[j] = temp;
		return array;
	}
	
	static class Person<T extends Number> implements Eat<String> { // extends-定义泛型上限（只能设置该类及其子类） super-定义泛型下限（只能设置该类及其父类）
		private String name;
		private T age;
		
		public Person() {
		}
		
		public Person(String name, T age) {
			super();
			this.name = name;
			this.age = age;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public T getAge() {
			return age;
		}
		public void setAge(T age) {
			this.age = age;
		}

		@Override
		public String toString() {
			return "Person [name=" + name + ", age=" + age + "]";
		}

		@Override
		public void eat(String t) {
			System.out.println(t);
		}
	}
	
	static interface Eat<T> {
		public void eat(T t);
	}
}
