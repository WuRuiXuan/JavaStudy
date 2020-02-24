package example.study.locale;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * 国际化
 */

public class LocaleDemo {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Locale locale = Locale.CHINA;
		Locale locale2 = new Locale("en", "US");
		
		ResourceBundle rb = ResourceBundle.getBundle("example.study.locale.language", locale);
//		ResourceBundle rb = ResourceBundle.getBundle("example.study.locale.language", locale2);
		
		System.out.println(rb.getString("welcome"));
		System.out.println(rb.getString("input.username"));
		String username = input.next();
		System.out.println(rb.getString("input.password"));
		String password = input.next();
		if (username.equals("abc") && password.equals("123")) {
			// 动态文本（给{0}占位符赋值）
			String info = MessageFormat.format(rb.getString("info"), "abc");
			System.out.println(info);
		}
	}

}
