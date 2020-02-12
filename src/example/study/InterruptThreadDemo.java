package example.study;

/**
 * 中断线程：自定义标记
 */

public class InterruptThreadDemo {

	public static void main(String[] args) {
		MyRunnable mr = new MyRunnable();
		Thread t1 = new Thread(mr);
		t1.start();
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		mr.flag = false;
	}
	
	static class MyRunnable implements Runnable {
		public boolean flag;
		
		public MyRunnable() {
			flag = true;
		}
		
		@Override
		public void run() {
			int i = 0;
			while (flag) {
				System.out.println("i = " + i);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				i ++;
//				if (i == 10) break;
			}
		}
	}
}
