package example.study.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket服务器端
 */

public class EchoServerDemo {

	public static void main(String[] args) {
		// 端口号从1024-65535
		try {
			ServerSocket ss = new ServerSocket(8000);
			System.out.println("服务器已启动，正在等待连接...");
			// 等待客户端连接，该方法会阻塞
			Socket socket = ss.accept();
			
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
			ss.close();
			System.out.println("服务器已关闭");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
