package example.study.reference;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 弱引用（WeakReference）
 * 如果某个对象与弱引用关联，那么当JVM在进行垃圾回收时，无论内存是否充足，都会回收该对象
 * 可以配合引用队列（ReferenceQueue）来释放弱引用自身
 */
public class WeakReferenceDemo {
	
	private static final int _4MB = 4 * 1024 * 1024;

	// -Xmx 20m
	public static void main(String[] args) {
		// list --> WeakReference --> byte[]
		List<WeakReference<byte[]>> list = new ArrayList<>();
		
		for (int i = 0; i < 6; i ++) {
			WeakReference<byte[]> ref = new WeakReference<>(new byte[_4MB]);
			list.add(ref);
			for (WeakReference<byte[]> w : list) {
				System.out.println(w.get());
			}
			System.out.println();
		}
		
		System.out.println("循环结束：" + list.size());
	}

}
