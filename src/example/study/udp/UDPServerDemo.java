package example.study.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * UDP发送端
 */

public class UDPServerDemo {

	public static void main(String[] args) {
		String info = "欢迎来到Java学习课堂";
		byte[] bytes = info.getBytes();
		// 封装数据包
		try {
			DatagramPacket dp = new DatagramPacket(bytes, 0, bytes.length, InetAddress.getByName("localhost"), 8000);
			DatagramSocket ds = new DatagramSocket(9000);
			ds.send(dp);
			ds.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
