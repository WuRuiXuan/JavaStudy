package example.study.reference;

/**
 * 终结器引用（FinalReference）
 * 无需手动编码，但其内部配合引用队列（ReferenceQueue）使用，在垃圾回收时，终结器引用入队（被引用对象暂时没有被回收），再由Finalizer线程通过
 * 终结器引用找到被引用对象，并调用它的finalize方法，第二次GC时才能回收被引用对象
 */
public class FinalReferenceDemo {

	public static void main(String[] args) {
		FinalReferenceDemo f = new FinalReferenceDemo();
		f = null;
		System.gc();
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("finalize");
	}
}
