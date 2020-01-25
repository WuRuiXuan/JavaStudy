package example.study;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 文件操作
 */

public class FileDemo {
	public static void main(String[] args) {
		fileInfo();
		filePath();
	}

	public static void fileInfo() {
		File file = new File(System.getProperty("user.dir") + "/coding.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("文件绝对路径：" + file.getAbsolutePath());
		long lastModified = file.lastModified();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String lastTime = df.format(new Date(lastModified));
		System.out.println("最后修改时间：" + lastTime);
		System.out.println("文件长度：" + file.length());
		System.out.println("是否为目录：" + file.isDirectory());
	}

	public static void filePath() {
		File file = new File(System.getProperty("user.dir"));
		if (!file.exists()) {
			file.mkdir();
		}
		// file.delete();
		String[] names = file.list();
		for (String name : names) {
			System.out.println("目录下文件名称: " + name);
		}

		File[] files = file.listFiles();
		for (File f : files) {
			System.out.println("目录下文件路径: " + f.getPath() + "--大小: " + f.length());
		}

		findFile(new File(System.getProperty("user.dir")), ".txt");
	}

	/**
	 *
	 * @param target 目标文件夹
	 * @param ext    扩展名
	 */
	public static void findFile(File target, String ext) {
		if (target != null) {
			if (target.isDirectory()) {
				File[] files = target.listFiles();
				if (files != null) {
					for (File f : files) {
						findFile(f, ext);
					}
				}
			} else {
				String path = target.getAbsolutePath();
				if (path.endsWith(ext)) {
					System.out.println("目标文件路径: " + path);
				}
			}
		}
	}
}
