package example.study.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Socket客户端
 */

public class EchoClientDemo {

	public static void main(String[] args) {
		// 连接服务器
		try {
			Socket socket = new Socket("localhost", 8000);
			System.out.println("连接成功！");
			boolean flag = true;
			
			OutputStream os = socket.getOutputStream();
			InputStream is = socket.getInputStream();
			PrintStream ps = new PrintStream(os);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			Scanner input = new Scanner(System.in);
			while (flag) {
				System.out.println("请输入：");
				String info = input.nextLine();
				if (info.equals("bye")) {
					flag = false;
					break;
				}
				ps.println(info);
				
				info = br.readLine();
				System.out.println(info);
			}
			
			os.close();
			is.close();
			System.out.println("连接关闭！");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
