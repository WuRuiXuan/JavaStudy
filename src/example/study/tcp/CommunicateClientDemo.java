package example.study.tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * 客户端之间通信
 */

public class CommunicateClientDemo {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 8000);
			System.out.println("连接成功！");
			
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			
			Scanner input = new Scanner(System.in);
			System.out.println("请输入姓名：");
			String name = input.nextLine();
			Info info = new Info();
			info.setFrom(name);
			info.setType(0); // 表示登录
			out.writeObject(info);
			info = (Info) in.readObject(); // 系统返回的消息
			System.out.println(info.getInfo());
			
			ReadInfoRunnable r = new ReadInfoRunnable(in);
			new Thread(r).start();
			
			// 输入消息
			while (true) {
				info = new Info();
				System.out.println("to：");
				info.setTo(input.nextLine());
				System.out.println("info: ");
				String str = input.nextLine();
				if (str.equals("bye")) {
					break;
				}
				else {
					info.setInfo(str);
					info.setFrom(name);
					info.setType(1);
					out.writeObject(info);
				}
			}
			
			r.flag = false; // 停止接收消息
			out.close();
			in.close();
			input.close();
			System.out.println("连接关闭！");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// 接收消息的线程
	static class ReadInfoRunnable implements Runnable {
		private ObjectInputStream in;
		public boolean flag;
		
		public ReadInfoRunnable(ObjectInputStream in) {
			this.in = in;
			flag = true;
		}

		@Override
		public void run() {
			while (flag) {
				try {
					Info info = (Info) in.readObject();
					System.out.println("接收到消息：" + info);
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
