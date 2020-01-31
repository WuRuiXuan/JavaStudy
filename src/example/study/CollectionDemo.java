package example.study;

import java.util.List;
import java.util.Queue;
import java.util.Stack;

import example.study.SetDemo.Student;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Collection（Vector、ArrayList、Set）
 * 迭代
 * 1. Iterator
 * 2. ListIterator
 * 3. Enumeration
 * 4. forEach
 * Collections--集合的工具类（类似Arrays）
 */

public class CollectionDemo {
	public static void main(String[] args) {
//		iterator();
//		fors();
//		forEach();
//		stack();
		queue();
	}
	
	// 可以迭代Set，统一的迭代方式
	public static void iterator() {
		List<Student> list = new ArrayList<>();
		Student s1 = new Student("xiaoruo", 18);
		Student s2 = new Student("vince", 19);
		Student s3 = new Student("xiaoming", 20);
		list.add(s1);
		list.add(s2);
		list.add(s3);
		
		Iterator<Student> iter = list.iterator();
		while (iter.hasNext()) {
			Student student = (Student) iter.next();
			System.out.println(student.toString());
		}
	}
	
	// 针对数组集合来说，使用for循环效率最高
	public static void fors() {
		List<Student> list = new ArrayList<>();
		Student s1 = new Student("xiaoruo", 18);
		Student s2 = new Student("vince", 19);
		Student s3 = new Student("xiaoming", 20);
		list.add(s1);
		list.add(s2);
		list.add(s3);
		
		int size = list.size(); // 不用每次循环都调用size()，提高性能
		for (int i = 0; i < size; i++) {
			Student student = (Student) list.get(i);
			System.out.println(student.toString());
		}
	}
	
	// 可以迭代Set，效率比Iterator更高
	public static void forEach() {
		List<Student> list = new ArrayList<>();
		Student s1 = new Student("xiaoruo", 18);
		Student s2 = new Student("vince", 19);
		Student s3 = new Student("xiaoming", 20);
		list.add(s1);
		list.add(s2);
		list.add(s3);
		
		for (Student student : list) {
			System.out.println(student.toString());
		}
	}
	
	// 堆栈
	public static void stack() {
		Stack<String> s = new Stack<>();
		s.push("vince"); // 压栈/入栈
		s.push("xiaoruo");
		
		String str = s.pop(); // 出栈（后进先出）
		System.out.println(str);
	}
	
	// 队列
	public static void queue() {
		Queue<String> q = new LinkedList<>();
		q.add("xiaoruo");
		q.add("vince");
		// 移除队列的头
		String str = q.poll(); // 后进后出
		System.out.println(str);
	}
}
