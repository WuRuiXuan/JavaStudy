package example.study;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/**
 * 统一资源定位符
 */

public class URLDemo {

	public static void main(String[] args) {
		try {
			URL url = new URL("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1582094638948&di=c7c4d0595397874029f4db5b88d68b3c&imgtype=0&src=http%3A%2F%2Fpic.qiantucdn.com%2F58pic%2F15%2F54%2F03%2F29A58PICKM3_1024.jpg%2521%2Ffw%2F780%2Fwatermark%2Furl%2FL3dhdGVybWFyay12MS4zLnBuZw%3D%3D%2Falign%2Fcenter%2Fcrop%2F1024x768a0a768");
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(System.getProperty("user.dir") + "/download.jpg"));
			
			byte[] bytes = new byte[1024];
			int len = -1;
			while ((len = bis.read(bytes)) != -1) {
				bos.write(bytes, 0, len);
			}
			bos.close();
			bis.close();
			System.out.println("download success!");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
