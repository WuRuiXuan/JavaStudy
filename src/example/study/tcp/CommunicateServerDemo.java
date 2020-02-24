package example.study.tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Socket服务器端转发通信消息给指定客户端
 */

public class CommunicateServerDemo {

	public static void main(String[] args) {
		ExecutorService es = Executors.newCachedThreadPool();
		// Vector线程安全
		Vector<ClientRunnable> list = new Vector<>();
		
		// 端口号从1024-65535
		try {
			ServerSocket ss = new ServerSocket(8000);
			System.out.println("服务器已启动，正在等待连接...");
			// 等待客户端连接，该方法会阻塞
			while (true) {
				Socket socket = ss.accept();
				ClientRunnable c = new ClientRunnable(socket, list);
				es.execute(c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 处理客户端请求的线程
	static class ClientRunnable implements Runnable {
		private Socket socket;
		private Vector<ClientRunnable> list;
		private String name;
		private ObjectOutputStream out;
		
		public ClientRunnable(Socket socket, Vector<ClientRunnable> list) {
			list.add(this);
			this.list = list;
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				System.out.println("客户端的IP为：" + socket.getInetAddress().getHostAddress());
				out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				
				while (true) {
					Info info = (Info) in.readObject();
					if (info.getType() == 0) { // 登录
						name = info.getFrom();
						Info welcome = new Info();
						welcome.setInfo("欢迎" + name);
						out.writeObject(welcome);
					}
					else if (info.getType() == 1) { // 转发消息
						for (int i = 0; i < list.size(); i++) {
							ClientRunnable c = list.get(i);
							if (c.name.equals(info.getTo())) {
								c.out.writeObject(info);
								break;
							}
						}
					}
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
