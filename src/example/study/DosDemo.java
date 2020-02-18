package example.study;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Java执行DOS命令
 */

public class DosDemo {

	public static void main(String[] args) {
		// 指定要执行的路径
		File dir = new File(System.getProperty("user.dir") + "\\bin");
		// 执行java实例
		String command = "java example.study.DelegateDemo";
		execDos(dir, command);
	}

	public static void execDos(File dir, String command) {
		String[] cmd = new String[] {"cmd", "/c", command};
		try {
			Process process = Runtime.getRuntime().exec(cmd, null, dir);
			// 记录dos命令的返回信息
			StringBuffer sb = new StringBuffer();
			// 获取返回信息的流
			InputStream is = process.getInputStream();
			Reader reader = new InputStreamReader(is, "GBK");
			BufferedReader br = new BufferedReader(reader);
			String res;
			while ((res = br.readLine()) != null) {
				sb.append(res + "\n");
			}
			System.out.println("cmd result: ");
			System.out.println(sb.toString());
			br.close();
			reader.close();
			process.getOutputStream().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
