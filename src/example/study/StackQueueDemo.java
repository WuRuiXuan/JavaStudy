package example.study;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 堆栈队列
 */

public class StackQueueDemo {

	public static void main(String[] args) {
		stack();
//		queue();
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
