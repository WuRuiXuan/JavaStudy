package example.study.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * 虚引用（PhantomReference）
 * 如果某个对象与虚引用关联，那么在任何时候都可能被JVM回收掉
 * 虚引用不能单独使用，必须配合引用队列（ReferenceQueue）一起使用
 * 主要配合ByteBuffer使用，被引用对象回收时，会将虚引用入队，由Reference Handler线程调用虚引用相关方法释放直接内存
 */
public class PhantomReferenceDemo {

	public static void main(String[] args) {
		ReferenceQueue<String> rq = new ReferenceQueue<>();
        PhantomReference<String> reference = new PhantomReference<>(new String("cord"), rq);
        System.out.println(reference.get());
        System.gc();
        System.runFinalization();
        System.out.println(rq.poll() == reference);
	}

}
