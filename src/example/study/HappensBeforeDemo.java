package example.study;

/**
 * happens-before规定了哪些写操作对其它线程的读操作可见，是可见性与有序性的一套规则总结
 */
public class HappensBeforeDemo {

	public static void main(String[] args) throws InterruptedException {
//		Condition1.show();
//		Condition2.show();
//		Condition3.show();
//		Condition4.show();
		Condition5.show();
	}
	
	// 1. 线程解锁之前对变量的写，对于接下来对该变量加锁的其它线程对该变量的读可见
	static class Condition1 {
		static int x;
		static Object m = new Object();
		
		public static void show() {
			
			new Thread(() -> {
				synchronized (m) {
					x = 10;
				}
			}, "t1").start();
			
			new Thread(() -> {
				synchronized (m) {
					System.out.println(x);
				}
			}, "t2").start();
			
		}
	}

	// 2. 线程对 volatile 变量的写，对接下来其它线程对该变量的读可见
	static class Condition2 {
		volatile static int x;
		
		public static void show() {
			
			new Thread(() -> {
				x = 10;
			}, "t1").start();
			
			new Thread(() -> {
				System.out.println(x);
			}, "t2").start();
			
		}
	}
	
	// 3. 线程 start 前对变量的写，对该线程开始后对该变量的读可见
	static class Condition3 {
		static int x;
		
		public static void show() {
			
			x = 10;
			
			new Thread(() -> {
				System.out.println(x);
			}, "t2").start();
			
		}
	}
	
	// 4. 线程结束前对变量的写，对其它线程得知它结束后的读可见
	static class Condition4 {
		static int x;
		
		public static void show() throws InterruptedException {
			
			Thread t1 = new Thread(() -> {
				x = 10;
			}, "t1");
			t1.start();
			// 线程没有执行完之前，会一直阻塞在join方法处
			t1.join();
			System.out.println(x);
		}
	}
	
	// 5. 线程 t1 打断（interrupt） t2 前对变量的写，对于其它线程得知 t2 被打断后对变量的读可见
	static class Condition5 {
		static int x;
		
		public static void show() {
			
			Thread t2 = new Thread(() -> {
				while (true) {
					if (Thread.currentThread().isInterrupted()) {
						System.out.println(x);
						break;
					}
				}
			}, "t2");
			t2.start();
			
			new Thread(() -> {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				x = 10;
				t2.interrupt();
			}, "t1").start();
			
			while (!t2.isInterrupted()) {
				// 线程让出当前时间片给其他线程执行
				Thread.yield();
			}
			System.out.println(x);
			
		}
	}
	
	// 6. 对变量默认值（0、false、null）的写，对其它线程对该变量的读可见
	// 7. 具有传递性，如果 x happens-before y 且 y happens-before z，那么有 x happens-before z
}
