package example.study;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 * List类
 */

public class ListDemo {
    public static void main(String[] args) {
    	arrayList();
    	vector();
    	linkedList();
    }

    public static void arrayList() {
    	// 对象数组的实现，默认数组大小为10，线程不安全，效率高，JDK1.2
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        System.out.println("size: " + list.size());
        
        for (int i = 0; i < list.size(); i++) {
			System.out.println("arrayList item" + i + ": " + list.get(i));
		}
        System.out.println("\n");
    }
    
    public static void vector() {
    	// 对象数组的实现，线程安全（同步），效率低，JDK1.0
		Vector<String> v = new Vector<>();
		v.add("Jack");
		v.add("Tom");
		v.add("Jerry");
		v.add("Vince");
		
		for (int i = 0; i < v.size(); i++) {
			System.out.println("vector item" + i + ": " + v.get(i));
		}
		System.out.println("\n");
	}
    
    public static void linkedList() {
    	// 双向链表的实现，适合删除、插入操作
		LinkedList<String> list = new LinkedList<>();
		list.add("Jack");
		list.add("Tom");
		list.add("Jerry");
		list.add("Vince");
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println("linkedList item" + i + ": " + list.get(i));
		}
		System.out.println("\n");
	}
}
