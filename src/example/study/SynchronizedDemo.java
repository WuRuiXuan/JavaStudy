package example.study;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程同步
 * 1. 同步代码块 
 * 2. 同步方法 
 * 3. Lock 同步会牺牲性能来换取安全
 */

public class SynchronizedDemo {
	public static void main(String[] args) {
//		syncCode();
//		syncMethod();
		lock();
	}

	public static void syncCode() {
		MyRunnable mr = new MyRunnable();
		Thread t1 = new Thread(mr);
		Thread t2 = new Thread(mr);
		t1.start();
		t2.start();
	}

	public static void syncMethod() {
		MethodSync methodSync = new MethodSync();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				methodSync.method("test1");
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				methodSync.method("test2");
			}
		});
		t1.start();
		t2.start();
	}

	public static void lock() {
		MyLock ml = new MyLock();
		Thread t1 = new Thread(ml);
		Thread t2 = new Thread(ml);
		t1.start();
		t2.start();
	}

	static class MyRunnable implements Runnable {
		private int flag;
		private Object obj = new Object();

		@Override
		public void run() {
			// 同步代码块
			synchronized (obj) {
				for (int i = 0; i < 5; i++) {
					flag = 0;
					System.out.println("开始打饭" + flag);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					flag = 1;
					System.out.println("饭已打完" + flag);
				}
			}
		}
	}

	static class MethodSync {
		// 同步方法：同步的锁对象是当前对象，当有线程正在调用method时，其他线程无法同时调用method及当前对象的其他同步方法，必须等待method被调用完毕后才能开始
		public synchronized void method(String name) {
			System.out.println(name + " Start a sync method");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(name + " End the sync method");
		}
	}

	static class MyLock implements Runnable {
		// 互斥锁（更可控，性能更高）
		private final ReentrantLock lock = new ReentrantLock();
		private int flag;

		@Override
		public void run() {
			lock.lock();// 上锁

			for (int i = 0; i < 5; i++) {
				flag = 0;
				System.out.println("开始打饭" + flag);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				flag = 1;
				System.out.println("饭已打完" + flag);
			}

			lock.unlock();// 解锁
		}
	}
}
