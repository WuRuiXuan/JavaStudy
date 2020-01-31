package example.study;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Set
 * 不包含重复元素的Collection
 */

public class SetDemo {
	public static void main(String[] args) {
//		hashSet();
//		treeSet();
		linkedHashSet();
	}
	
	// 先计算hashCode，再通过equals方法判断是否重复，遍历顺序可能会变
	public static void hashSet() {
		Set<String> set = new HashSet<>();
		set.add("xiaoruo");
		set.add("vince");
		set.add("xiaoruo");
		System.out.println("size: " + set.size());
		System.out.println("----------------");
		
		Set<Student> set2 = new HashSet<>();
		Student s1 = new Student("xiaoruo", 18);
		Student s2 = new Student("vince", 19);
		Student s3 = new Student("xiaoruo", 18);
		set2.add(s1);
		set2.add(s2);
		set2.add(s1); // 添加不进
		set2.add(s3); // 可以添加，如果要改为值相同就不能添加，需要重写hashCode和equals方法
		System.out.println("size: " + set2.size());
		for (Student student : set2) {
			System.out.println(student.toString());
		}
		System.out.println("----------------");
	}
	
	// 通过compareTo方法判断是否重复，元素需实现Comparable接口
	public static void treeSet() {
		Set<String> set = new TreeSet<>();
		set.add("xiaoruo");
		set.add("vince");
		set.add("xiaoruo");
		System.out.println("size: " + set.size());
		System.out.println("----------------");
		
		Set<Cat> set2 = new TreeSet<>();
		Cat c1 = new Cat("xiaoruo", 18);
		Cat c2 = new Cat("vince", 19);
		Cat c3 = new Cat("xiaoruo", 18);
		set2.add(c1);
		set2.add(c2);
		set2.add(c3);
		System.out.println("size: " + set2.size());
		for (Cat cat : set2) {
			System.out.println(cat.toString());
		}
		System.out.println("----------------");
	}
	
	// 继承自HashSet，确保对象的插入顺序，由双向链表+Hash表实现
	public static void linkedHashSet() {
		Set<String> set = new LinkedHashSet<>();
		set.add("xiaoruo");
		set.add("vince");
		set.add("xiaoruo");
		System.out.println("size: " + set.size());
		for (String string : set) {
			System.out.println(string);
		}
		System.out.println("----------------");
	}
	
	public static class Student {
		private String name;
		private int age;
		
		public Student(String name, int age) {
			super();
			this.name = name;
			this.age = age;
		}
		
		// Generate hashCode() and equals()
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + age;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			System.out.println("hashCode: " + result);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			System.out.println("equals");
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Student other = (Student) obj;
			if (age != other.age)
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Student [name=" + name + ", age=" + age + "]";
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
	}
	
	static class Cat implements Comparable<Cat> {
		private String name;
		private int age;
		
		public Cat(String name, int age) {
			super();
			this.name = name;
			this.age = age;
		}

		@Override
		public int compareTo(Cat o) {
			if (age < o.age) {
				return -1;
			} else if (age > o.age) {
				return 1;
			}
			return 0;
		}
		
		@Override
		public String toString() {
			return "Cat [name=" + name + ", age=" + age + "]";
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
	}
}
