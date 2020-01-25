package example.study;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

/**
 * 缓冲流
 */

public class BufferedStreamDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		byteStreamOut();
//		byteStreamIn();
		
		charStreamOut();
		charStreamIn();
	}
	
	public static void byteStreamOut() {
		try {
			OutputStream out = new FileOutputStream(System.getProperty("user.dir") + "/coding.txt");
			BufferedOutputStream bos = new BufferedOutputStream(out);
			// 缓存在内存中
			bos.write("让编程更简单".getBytes());
			// 写入文件
			bos.close();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void byteStreamIn() {
		try {
			InputStream in = new FileInputStream(System.getProperty("user.dir") + "/coding.txt");
			BufferedInputStream bis = new BufferedInputStream(in);
			byte[] bytes = new byte[1024];
			int len = -1;
			StringBuffer bf = new StringBuffer();
			while ((len = bis.read(bytes)) != -1) {
				bf.append(new String(bytes, 0, len));
			}
			bis.close();
			in.close();
			System.out.println(bf);
		}
		catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void charStreamOut() {
		try {
			Writer out = new FileWriter(System.getProperty("user.dir") + "/coding.txt");
			// 自带缓存功能
			BufferedWriter bw = new BufferedWriter(out);
			bw.write("让编程更简单");
			bw.close();
			out.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void charStreamIn() {
		try {
			Reader in = new FileReader(System.getProperty("user.dir") + "/coding.txt");
			BufferedReader br = new BufferedReader(in);
			String info = br.readLine();
			br.close();
			in.close();
			System.out.println(info);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
