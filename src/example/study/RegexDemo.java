package example.study;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式
 */

public class RegexDemo {

	public static void main(String[] args) {
		String s = "0487561";
		boolean flag = true;
		
//		char[] cs = s.toCharArray();
//		for (int i = 0; i < cs.length; i++) {
//			if (cs[i] < '0' || cs[i] > '9') {
//				flag = false;
//				break;
//			}
//		}
//		if (flag) {
//			System.out.println("是由数组组成");
//		}
//		else {
//			System.out.println("不是由数组组成");
//		}
		
		// 使用正则表达式
		flag = s.matches("\\d+");
		
//		Pattern p = Pattern.compile("\\d+");
//		Matcher m = p.matcher(s);
//		flag = m.matches();
		
		if (flag) {
			System.out.println("是由数组组成");
		}
		else {
			System.out.println("不是由数组组成");
		}
	}

	// 匹配电话号码：如 010-38389438
	public static boolean isTelephoneNumber(String str) {
		return str.matches("\\d{3,4}-\\d{7,8}");
	}
	
	// 匹配手机号码：如 13895271234
	public static boolean isPhoneNumber(String str) {
		return str.matches("[1][3-9]\\d{9}");
	}
	
	// 匹配用户名：字母开头，数字字母下划线组合
	public static boolean isUsername(String str) {
		return str.matches("[a-zA-Z]+[\\w|_]*");
	}
	
	// 匹配IP地址
	public static boolean isIPAddress(String str) {
		return str.matches("\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}");
	}
	
	// 匹配网址
	public static boolean isURL(String str) {
		return str.matches("http://\\w+.\\w+.\\S*");
	}
	
	// 匹配年龄
	public static boolean isAge(String str) {
		return str.matches("\\d{1,3}");
	}
	
	// 匹配金额
	public static boolean isMoney(String str) {
		return str.matches("\\d+.\\d{0,2}");
	}
}
