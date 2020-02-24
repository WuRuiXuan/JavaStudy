package example.study;

/**
 * StringBuffer类
 * 1. 使用String连接字符串，性能会非常低，因为String的内容不可改变。解决这个问题的方法是使用StringBuffer
 * 2. StringBuilder是StringBuffer的简易替换，不同步，线程不安全，建议单线程使用，比StringBuffer更快
 */

public class StringBufferDemo {

	public static void main(String[] args) {
		// 拼接字符串（性能低）
		String s = "Hello";
		String ss = s + "World";
		System.out.println(ss);
		
		// 默认创建一个大小为16的字符数组，每次扩充容量都乘以2倍加2，
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 17; i++) {
			sb.append(i % 10);
		}
		System.out.println(sb.toString());
		System.out.println("capacity: " + sb.capacity());
		
		// 指定容量创建（节省内存）
		StringBuffer sb2 = new StringBuffer(17);
		for (int i = 0; i < 17; i++) {
			sb2.append(i % 10);
		}
		System.out.println(sb2.toString());
		System.out.println("capacity: " + sb2.capacity());
		
		// 使用StringBuilder（速度更快）
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 17; i++) {
			builder.append(i % 10);
		}
		System.out.println(builder.toString());
	}

}
