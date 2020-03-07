package example.study.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 */

public class ExecutorDemo {

	public static void main(String[] args) {
		// 创建一个单线程的执行器
//		ExecutorService es = Executors.newSingleThreadExecutor();
		// 创建一个固定数量的线程执行器
//		ExecutorService es = Executors.newFixedThreadPool(3);
		// 创建一个可缓存线程的执行器（60秒空闲将被回收）
		ExecutorService es = Executors.newCachedThreadPool();
		// 创建一个大小无限的线程池
//		ExecutorService es = Executors.newScheduledThreadPool(3);
		
		es.execute(new DownloadRunnable());
		es.execute(new DownloadRunnable());
		es.execute(new DownloadRunnable());
		es.execute(new DownloadRunnable());
	}

	static class DownloadRunnable implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i <= 10; i++) {
				System.out.println(Thread.currentThread().getName() + "已下载" + i * 10 + "%");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
