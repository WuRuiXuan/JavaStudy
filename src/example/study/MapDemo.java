package example.study;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import example.study.SetDemo.Cat;

/**
 * Map类
 * 操作两个对象的集合（键值对）
 * HashMap
 * 1. 使用Hash表（数组+链表）实现，键不能重复，如果相同就会覆盖原来的对象
 * 2. 默认创建一个长度为16的数组，加载因子为0.75（当数组容量满75%后，需要重新扩充，又叫哈希表重新散列）
 * 3. 哈希表保存对象时，会根据Key对象的hashCode值对HashMap的容量求余，决定该对象要存在数组的哪个位置，如果该位置有对象，那么这些对象以链表的结构存储
 */

public class MapDemo {
	public static void main(String[] args) {
		hashMap();
		iterator();
	}
	
	// 异步，线程不安全，效率高，初始容量为16，每次扩充2倍
	public static void hashMap() {
		Map<String, String> map = new HashMap<>();
		map.put("1", "xiaoruo");
		map.put("2", "vince");
		map.put("1", "jack");
		
		System.out.println("size: " + map.size());
		System.out.println("\n");
		
		// 取值
		System.out.println("item1: " + map.get("1") + " 存的位置是: " + "1".hashCode() % 16);
		System.out.println("item2: " + map.get("2") + " 存的位置是: " + "2".hashCode() % 16);
		System.out.println("\n");
		
		System.out.println("containsKey: " + map.containsKey("1"));
		System.out.println("\n");
	}
	
	// 同步，线程安全，效率低，初始容量为11，每次扩充2倍+1
	public static void hashTable() {
		Hashtable<String, String> table = new Hashtable<>();
		table.put("1", "xiaoruo");
	}
	
	// 二叉树数据结构的实现（红黑树--平衡二叉树），存的对象需实现Comparable接口
	public static void treeMap() {
		TreeMap<String, Cat> map = new TreeMap<>();
		map.put("1", new Cat("tom", 2));
	}
	
	// 迭代
	public static void iterator() {
		HashMap<String, Cat> map = new HashMap<>();
		map.put("jack", new Cat("jack", 2));
		map.put("mimi", new Cat("mimi", 3));
		map.put("miaomiao", new Cat("miaomiao", 5));
		
		// 方式1
		Set<String> set = map.keySet();
		Iterator<String> iter = set.iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			Cat cat = map.get(key);
			System.out.println(key + "-->" + cat.toString());
		}
		
		System.out.println();
		
		// 方式2
		Set<Entry<String, Cat>> set2 = map.entrySet();
		Iterator<Entry<String, Cat>> iter2 = set2.iterator();
		while (iter2.hasNext()) {
			Entry<String, Cat> entry = iter2.next();
			System.out.println(entry.getKey() + "->" + entry.getValue());
		}
	}
}
