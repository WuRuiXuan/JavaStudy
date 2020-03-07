package example.study.io;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * 打印流
 */

public class PrintStreamDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		charStreamOut();
//		byteStreamOut();
	}
	
	public static void charStreamOut() {
		try {
			Writer out = new FileWriter(System.getProperty("user.dir") + "/coding.txt");
			BufferedWriter bw = new BufferedWriter(out);
			PrintWriter pw = new PrintWriter(bw);
			pw.println(97);
			pw.println("abc");
			pw.println(true);
			pw.close();
			bw.close();
			out.close();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void byteStreamOut() {
		try {
			OutputStream out = new FileOutputStream(System.getProperty("user.dir") + "/coding.txt");
			// 让字节输出流具备缓冲功能
			BufferedOutputStream bos = new BufferedOutputStream(out);
			// 更方便输出
			PrintStream ps = new PrintStream(bos);
			ps.println(123);
			ps.println("abc");
			ps.println(true);
			ps.close();
			bos.close();
			out.close();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
