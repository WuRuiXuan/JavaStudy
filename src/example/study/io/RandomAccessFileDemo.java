package example.study.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 随机访问文件（随机是指程序可以直接跳到文件的任意位置来读写数据）
 */

public class RandomAccessFileDemo {

	public static void main(String[] args) {
		fileWrite();
		fileRead();
	}

	public static void fileWrite() {
		try {
			// mode: r - 只读 rw - 读写 rws - 文件内容或元数据即时同步 rwd - 文件内容即时同步
			RandomAccessFile raf = new RandomAccessFile(new File(System.getProperty("user.dir") + "/coding.txt"), "rw");
			raf.writeInt(18);
			raf.writeUTF("小若");
			raf.writeBoolean(false);
			raf.writeChar('女');
			raf.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void fileRead() {
		try {
			// mode: r - 只读 rw - 读写 rws - 文件内容或元数据即时同步 rwd - 文件内容即时同步
			RandomAccessFile raf = new RandomAccessFile(new File(System.getProperty("user.dir") + "/coding.txt"), "rw");
			raf.seek(0); // 定位到文件的开始位置
			int age = raf.readInt();
			String name = raf.readUTF();
			raf.skipBytes(1); // 跳过1个字节（不读取Boolean值）
			char sex = raf.readChar();
			System.out.println("姓名：" + name + "\n年龄：" + age + "\n性别：" + sex);
			raf.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
