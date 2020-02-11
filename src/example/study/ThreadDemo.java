package example.study;

/**
 * 进程：一个具有独立内存空间的执行程序
 * 线程：进程中的其中一条执行路径（多条就是多线程），每个进程最少有一个线程，同一个进程中的多个线程共享同一个内存空间
 * 实现多线程的两种方式：
 * 1. 继承Thread类
 * 2. 实现Runnable接口（推荐）
 */

public class ThreadDemo {
	public static void main(String[] args) {
		thread();
//		runnable();
	}
	
	public static void thread() {
		System.out.println(Thread.currentThread().getName());
		// 创建一个线程对象
		MyThread t1 = new MyThread();
		// 设置抢占CPU执行时间的优先级（注意，只是几率大）
		t1.setPriority(Thread.MAX_PRIORITY);
		// 把t1设置为守护线程（所有非守护线程执行完毕后t1停止执行）
//		t1.setDaemon(true);
		// 直接调用不是启动线程，而是在当前线程中执行方法
//		t1.run();
		// 线程已准备就绪，等待CPU的调度
		t1.start();
		
//		join(t1);
		yield(t1);
	}
	
	public static void join(Thread t1) {
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "-" + i);
			if (i == 6) {
				try {
					// 等待t1线程执行完再继续执行
					t1.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void yield(Thread t1) {
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "-" + i);
			if (i == 6) {
				// 让出当次CPU的执行时间
				Thread.yield();
			}
		}
	}
	
	public static void runnable() {
		MyRunnable r = new MyRunnable();
		Thread t2 = new Thread(r, "MyRunnable");
		t2.start();
//		System.out.println("isAlive: " + t2.isAlive());
	}
	
	// 继承Thread类
	static class MyThread extends Thread {
		@Override
		public void run() {
			// 在此方法中编写线程要执行的工作
			for (int i = 0; i < 10; i++) {
				try {
					// 在指定的毫秒数内让当前正在执行的线程休眠（暂停执行）
					// 此操作受系统计时器和调度程序精度和准确性影响。该线程不丢失任何监视器的所属权。
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "-" + System.currentTimeMillis() + "-" + i);
			}
		}
	}
	
	// 实现Runnable接口
	static class MyRunnable implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "-" + System.currentTimeMillis() + "-" + i);
			}
		}
	}
}
