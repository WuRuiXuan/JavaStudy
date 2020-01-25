package example.study;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * 字节数组流
 */

public class ByteArrayStreamDemo {

	public static void main(String[] args) {
		byteArrayStream();
	}

	// 在内存中读写
	public static void byteArrayStream() {
		String info = "Java从入门到放弃";
		ByteArrayInputStream bis = new ByteArrayInputStream(info.getBytes());
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int i = -1;
		try {
			while ((i = bis.read()) != -1) {
				bos.write(i);
			}
			System.out.println(bos.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
