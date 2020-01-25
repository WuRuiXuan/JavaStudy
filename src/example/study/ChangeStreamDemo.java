package example.study;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.io.InputStream;
import java.io.InputStreamReader;
//import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
//import java.util.Scanner;

/**
 * 转换流
 */

public class ChangeStreamDemo {
	public static void main(String[] args) {
//		reader();
//		bfReader();
		writer();
	}

	// 转换成字符流
	public static void reader() {
//		Scanner scanner = new Scanner(System.in);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + "/DemoText.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reader reader = null;
		try {
			reader = new InputStreamReader(fis, "GBK");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		char[] cs = new char[1024];
		int len = -1;
		StringBuffer bf = new StringBuffer();
		try {
			while ((len = reader.read(cs)) != -1) {
				bf.append(new String(cs, 0, len));
			}
			reader.close();
			String text = bf.toString();
			System.out.println("文件的内容是：" + text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void bfReader() {
		BufferedReader bfReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("请输入一串文字，可包括空格：");
		try {
			String text = bfReader.readLine();
			System.out.println("输入的文字是：" + text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writer() {
		Writer writer = new OutputStreamWriter(System.out);
		try {
			writer.write("Hello World!");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
