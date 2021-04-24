package example.study.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 软引用（SoftReference）
 * 如果某个对象与软引用关联，那么在垃圾回收后，内存仍不足时，JVM会再次发出垃圾回收，回收该对象
 * 可以配合引用队列（ReferenceQueue）来释放软引用自身
 */
public class SoftReferenceDemo {
	
	private static final int _4MB = 4 * 1024 * 1024;

	// -Xmx 20m
	public static void main(String[] args) {
		List<SoftReference<byte[]>> list = new ArrayList<>();
		
		// 引用队列
		ReferenceQueue<byte[]> queue = new ReferenceQueue<>();
		
		for (int i = 0; i < 5; i ++) {
			// 关联了引用队列，当软引用所关联的byte[]被回收时，软引用自身会加入到queue中去
			SoftReference<byte[]> ref = new SoftReference<>(new byte[_4MB], queue);
			System.out.println(ref.get());
			list.add(ref);
			System.out.println(list.size());
		}
		
		// 从队列中获取无用的软引用对象，并移除
		Reference<? extends byte[]> poll = queue.poll();
		while (poll != null) {
			list.remove(poll);
			poll = queue.poll();
		}
		
		System.out.println("-------------------------------");
		for (SoftReference<byte[]> ref : list) {
			System.out.println(ref.get());
		}
	}

}
