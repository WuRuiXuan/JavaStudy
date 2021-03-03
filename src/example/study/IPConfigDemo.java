package example.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * @author
 * @date
 * @version java代码 调用dos的ipconfig /all 命令，获取网卡详细信息
 */

public class IPConfigDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		ArrayList<String> hostName = getLocalMachineInfo("Windows IP 配置", "主机名");
//		ArrayList<String> wlanLocalAddress = getLocalMachineInfo("无线局域网适配器 本地连接", "物理地址");
//		ArrayList<String> wlanAddress = getLocalMachineInfo("无线局域网适配器 WLAN", "物理地址");
		ArrayList<String> lanAddress = getLocalMachineInfo("以太网适配器 以太网", "物理地址");
//		ArrayList<String> bluetoothAddress = getLocalMachineInfo("以太网适配器 蓝牙网络连接", "物理地址");
		
//		System.out.println("主机名:" + hostName.toString());
//		System.out.println("无线局域网适配器 本地连接 物理地址:" + wlanLocalAddress.toString());
//		System.out.println("无线局域网适配器 WLAN 物理地址:" + wlanAddress.toString());
		System.out.println("以太网适配器 以太网 物理地址:" + lanAddress.toString());
//		System.out.println("以太网适配器 蓝牙网络连接 物理地址:" + bluetoothAddress.toString());
	}

	static ArrayList<String> getLocalMachineInfo(String str1, String str2) {
		ArrayList<String> results = new ArrayList<>();
		String line = "";
		int n;
		boolean isGroup = false;
		try {
			Process ps = Runtime.getRuntime().exec("cmd /c ipconfig /all");
			BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream(), Charset.forName("GBK")));
			while (null != (line = br.readLine())) {
//				System.out.println(line);
				if (line.indexOf(str1) != -1 && str1.equals(line.substring(0,line.indexOf(":")))) {
					isGroup = true;
				}
				if (isGroup && line.indexOf(str2) != -1) {
					n = line.indexOf(":");
					results.add(line.substring(n + 2));
					isGroup = false;
				}
			}
			ps.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}
}