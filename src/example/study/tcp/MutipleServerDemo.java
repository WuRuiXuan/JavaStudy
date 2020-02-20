package example.study.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 多客户端的服务器端
 */

public class MutipleServerDemo {

	public static void main(String[] args) {
		ExecutorService es = Executors.newCachedThreadPool();
		// 端口号从1024-65535
		try {
			ServerSocket ss = new ServerSocket(8000);
			System.out.println("服务器已启动，正在等待连接...");
			// 等待客户端连接，该方法会阻塞
			while (true) {
				Socket socket = ss.accept();
				es.execute(new ClientRunnable(socket));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 处理客户端请求的线程
	static class ClientRunnable implements Runnable {
		private Socket socket;
		
		public ClientRunnable(Socket socket) {
			super();
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				System.out.println("客户端的IP为：" + socket.getInetAddress().getHostAddress());
				InputStream is = socket.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				OutputStream os = socket.getOutputStream();
				PrintStream ps = new PrintStream(os);
				while (true) {
					String info = br.readLine();
					if (info == null || info.equals("bye")) {
						break;
					}
					System.out.println("read: " + info);
					ps.println("echo: " + info);
				}
				os.close();
				is.close();
				System.out.println("客户端断开");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
