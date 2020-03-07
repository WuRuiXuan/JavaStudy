package example.study.thread;

/**
 * 死锁
 */

public class DeadThreadDemo {

	public static void main(String[] args) {
		// 有一定几率产生死锁
		new DeadThread();
	}

	// 顾客
	static class Customer {
		public synchronized void say(Waiter w) {
			System.out.println("顾客说：先吃饭再买单！");
			w.doService();
		}

		public synchronized void doService() {
			System.out.println("同意了，买完单再吃饭！");
		}
	}

	// 服务员
	static class Waiter {
		public synchronized void say(Customer c) {
			System.out.println("服务员说：先买单再吃饭！");
			c.doService();
		}

		public synchronized void doService() {
			System.out.println("同意了，吃完饭再买单！");
		}
	}
	
	// 死锁线程
	static class DeadThread implements Runnable {
		Customer c = new Customer();
		Waiter w = new Waiter();
		
		public DeadThread() {
			new Thread(this).start();
			w.say(c);
		}
		
		@Override
		public void run() {
			c.say(w);
		}
	}
}
