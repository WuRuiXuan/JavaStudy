package example.study;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

/**
 * NIO：面向块的I/O，速度快
 * 1. 内存映射读写（最快）
 * 2. NIO文件通道读写（第二快）
 * 3. 传统的IO读写（最慢）
 */

public class NIODemo {
	public static void main(String[] args) {
		File src = new File(System.getProperty("user.dir") + "/coding.txt");
		File dest = new File(System.getProperty("user.dir") + "/copied.txt");
		
//		byteBufferUse();
//		fileCopy(src, dest);
		randomAccessFileCopy(src, dest);
	}
	
	public static void byteBufferUse() {
		ByteBuffer buf = ByteBuffer.allocate(10);
		System.out.println("position = " + buf.position());
		System.out.println("limit = " + buf.limit());
		System.out.println("capacity = " + buf.capacity());
		System.out.println("----------------");
		
		buf.put((byte) 10);
		byte[] bs = {20, 30, 40};
		buf.put(bs);
		System.out.println("position = " + buf.position());
		System.out.println("limit = " + buf.limit());
		System.out.println("capacity = " + buf.capacity());
		System.out.println("----------------");
		
		// 反转：position回到0 limit移到position的位置
		buf.flip();
		System.out.println("position = " + buf.position());
		System.out.println("limit = " + buf.limit());
		System.out.println("capacity = " + buf.capacity());
		System.out.println("----------------");
		
		// 取值
		// remaining()返回当前位置与限制之间的元素数
		for (int i = 0; i < buf.remaining(); i++) { 
			System.out.println("index = " + i + " value = " + buf.get(i));
		}
	}
	
	// NIO文件通道读写
	public static void fileCopy(File src, File dest) {
		try {
			FileInputStream in = new FileInputStream(src);
			FileOutputStream out = new FileOutputStream(dest);
			FileChannel inChannel = in.getChannel(); // 获取文件通道
			FileChannel outChannel = out.getChannel();
			
			ByteBuffer buf = ByteBuffer.allocate((int)src.length());
			inChannel.read(buf);
			buf.flip();
			outChannel.write(buf);
			
			inChannel.close();
			outChannel.close();
			in.close();
			out.close();
			
			System.out.println("拷贝完成");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 内存映射读写
	public static void randomAccessFileCopy(File src, File dest) {
		try {
			RandomAccessFile in = new RandomAccessFile(src, "r");
			RandomAccessFile out = new RandomAccessFile(dest, "rw");
			FileChannel inChannel = in.getChannel();
			FileChannel outChannel = out.getChannel();
			
			long size = inChannel.size();
			
			MappedByteBuffer inBuf = inChannel.map(MapMode.READ_ONLY, 0, size);
			MappedByteBuffer outBuf = outChannel.map(MapMode.READ_WRITE, 0, size);
//			outChannel.write(inBuf);
//			inBuf.flip();
			for (int i = 0; i < size; i++) {
				byte b = inBuf.get(i);
				outBuf.put(i, b);
			}
			
			inChannel.close();
			outChannel.close();
			in.close();
			out.close();
			
			System.out.println("拷贝完成");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
