package example.study;

import java.io.UnsupportedEncodingException;

/**
 * 常见字符编码
 * iso-8859-1 英文编码，主要用于服务器端
 * GBK/GB2312 中文国际编码，双字节
 * unicode    16进制编码，英文也需要使用双字节，不兼容iso-8859-1
 * UTF        表示所有语言字符，不定长编码，1-6字节，兼容iso-8859-1
 */

public class CodeDemo {
	public static void main(String[] args) {
		String info = "Java从入门到放弃";
		try {
			// 转换编码
			String newInfo = new String(info.getBytes("GBK"), "iso-8859-1");
			System.out.println(newInfo);
			// 还原
			String str = new String(newInfo.getBytes("iso-8859-1"), "GBK");
			System.out.println(str);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
