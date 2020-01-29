package example.study;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 数据流
 */

public class DataStreamDemo {
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		dataOut();
		dataIn();
	}
	
	public static void dataOut() {
		try {
			OutputStream os = new FileOutputStream(System.getProperty("user.dir") + "/coding.txt");
			DataOutputStream dos = new DataOutputStream(os);
			dos.writeInt(10); // 写入4字节
			dos.writeUTF("Java从入门到放弃");
			dos.writeLong(12345678);
			dos.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void dataIn() {
		try {
			InputStream is = new FileInputStream(System.getProperty("user.dir") + "/coding.txt");
			DataInputStream dis = new DataInputStream(is);
			int a = dis.readInt();
			String str = dis.readUTF();
			long b = dis.readLong();
			System.out.println(a);
			System.out.println(str);
			System.out.println(b);
			dis.close();
			is.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
