package example.study;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * CAS乐观锁：
 * CAS 是英文单词 Compare And Swap 的缩写，翻译过来就是比较并替换，体现一种乐观锁的思想
 * 在多个线程对一个共享变量执行操作时，结合 CAS 和 volatile 可以实现无锁并发，适用于竞争不激烈、多核 CPU 的场景
 * CAS 机制当中使用了3个基本操作数：内存地址 V 、旧的预期值 A 、要修改的新值 B
 * 更新一个共享变量的时候，只有当旧的预期值 A 和内存地址 V 当中的实际值相同时，才会将内存地址 V 对应的值修改为 B
 */
public class CASDemo {

	public static void main(String[] args) throws InterruptedException {
		DataContainer dc = new DataContainer();
		int count = 5;
		Thread t1 = new Thread(() -> {
			for (int i = 0; i < count; i++) {
				dc.increase();
			}
		});
		Thread t2 = new Thread(() -> {
			for (int i = 0; i < count; i++) {
				dc.decrease();
			}
		});
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(dc.getData());
	}
	
	static class DataContainer {
		private volatile int data;
		static final Unsafe unsafe;
		static final long DATA_OFFSET;
		
		static {
			try {
				// Unsafe 对象不能直接调用，只能通过反射获得
				Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
				theUnsafe.setAccessible(true);
				unsafe = (Unsafe) theUnsafe.get(null);
			} catch (NoSuchFieldException | IllegalAccessException e) {
				throw new Error(e);
			}
			try {
				// data 属性在 DataContainer 对象中的偏移量，用于 Unsafe 直接访问该属性
				DATA_OFFSET = unsafe.objectFieldOffset(DataContainer.class.getDeclaredField("data"));
			} catch (NoSuchFieldException e) {
				throw new Error(e);
			}
		}
		
		public void increase() {
			int oldValue;
			while (true) {
				// 获取共享变量旧值，可以在这一行加入断点，修改 data 调试来加深理解
				oldValue = data;
				// CAS 尝试修改 data 为 旧值+1 ，如果期间旧值被别的线程改了，返回 false
				if (unsafe.compareAndSwapInt(this, DATA_OFFSET, oldValue, oldValue + 1)) {
					return;
				}
			}
		}
		
		public void decrease() {
			int oldValue;
			while (true) {
				oldValue = data;
				if (unsafe.compareAndSwapInt(this, DATA_OFFSET, oldValue, oldValue - 1)) {
					return;
				}
			}
		}
		
		public int getData() {
			return data;
		}
	}

}
