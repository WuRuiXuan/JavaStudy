package example.study;

/**
 * 多线程生产者与消费者案例
 */

public class ThreadExampleDemo {

	public static void main(String[] args) {
		Food food = new Food();
		Producer p = new Producer(food);
		Customer c = new Customer(food);
		Thread t1 = new Thread(p);
		Thread t2 = new Thread(c);
		t1.start();
		t2.start();
	}
	
	// 生产者
	static class Producer implements Runnable {
		private Food food;
		
		public Producer(Food food) {
			this.food = food;
		}

		@Override
		public void run() {
			for (int i = 0; i < 50; i++) {
				if (i % 2 == 0) {
//					food.setName("银耳莲子汤");
//					try {
//						Thread.sleep(500);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					food.setEfficacy("功效：美容养颜");
					food.set("银耳莲子汤", "功效：美容养颜");
				} else {
//					food.setName("糖醋里脊");
//					try {
//						Thread.sleep(500);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					food.setEfficacy("功效：酸甜可口");
					food.set("糖醋里脊", "功效：酸甜可口");
				}
			}
		}
	}
	
	// 消费者
	static class Customer implements Runnable {
		private Food food;
		
		public Customer(Food food) {
			this.food = food;
		}

		@Override
		public void run() {
			for (int i = 0; i < 50; i++) {
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				System.out.println(food.getName() + "->" + food.getEfficacy());
				food.get();
			}
		}
	}
	
	// 消费的对象（数据）
	static class Food {
		private String name; // 菜名
		private String efficacy; // 功效
		private boolean flag = true; // true表示可以生产不能消费，false表示可以消费不能生产
		
		public Food() {
			
		}
		
		public Food(String name, String efficacy) {
			super();
			this.name = name;
			this.efficacy = efficacy;
		}
		
		public synchronized void set(String name, String efficacy) {
			if (!flag) {
				try {
					this.wait(); // 当前线程进入等待状态，让出CPU，并释放该监视器上的锁
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			this.setName(name);
			try {
				Thread.sleep(500); // 不会释放该监视器上的锁
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.setEfficacy(efficacy);
			flag = false;
//			this.notifyAll(); // 唤醒该监视器上的其他所有线程（所有被唤醒的线程都将争夺锁定）
			this.notify(); // 唤醒该监视器上的其他一个线程
		}
		
		public synchronized void get() {
			if (flag) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(this.getName() + "->" + this.getEfficacy());
			flag = true;
			this.notify();
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEfficacy() {
			return efficacy;
		}

		public void setEfficacy(String efficacy) {
			this.efficacy = efficacy;
		}
	}
}
