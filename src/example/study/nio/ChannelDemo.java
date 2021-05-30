package example.study.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 通道（Channel）：用于源节点与目标节点的连接，负责缓冲区中数据的传输，本身不存储数据，因此需要配合缓冲区进行传输
 *
 */
public class ChannelDemo {

	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();

//		testFileCopyByChannel();
//		testFileCopyByDirect();
//		testTransfer();
//		testScatterAndGather();
//		testAvailableCharsets();
		testCharsetEncoderAndDecoder();

		long endTime = System.currentTimeMillis();

		System.out.println("耗费时间为：" + (endTime - startTime) + "毫秒");
	}

	// 利用通道完成文件的复制（非直接缓冲区）
	public static void testFileCopyByChannel() {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		FileChannel inChannel = null;
		FileChannel outChannel = null;

		try {
			fis = new FileInputStream(System.getProperty("user.dir") + "/coding.txt");
			fos = new FileOutputStream(System.getProperty("user.dir") + "/copied.txt");

			// 获取通道
			inChannel = fis.getChannel();
			outChannel = fos.getChannel();

			// 分配指定大小的缓冲区
			ByteBuffer buf = ByteBuffer.allocate(1024);

			// 将通道中的数据存入缓冲区中
			while (inChannel.read(buf) != -1) {
				// 切换成读取数据的模式
				buf.flip();
				// 将缓冲区中的数据写入通道中
				outChannel.write(buf);
				// 清空缓冲区
				buf.clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (outChannel != null) {
				try {
					outChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (inChannel != null) {
				try {
					inChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 利用直接缓冲区完成文件的复制（内存映射文件）
	public static void testFileCopyByDirect() throws IOException {
		FileChannel inChannel = FileChannel.open(Paths.get(System.getProperty("user.dir") + "/coding.txt"),
				StandardOpenOption.READ);
		// StandardOpenOption.CREATE 无论文件是否存在，都重新创建
		// StandardOpenOption.CREATE_NEW 文件不存在就创建，存在就报错
		FileChannel outChannel = FileChannel.open(Paths.get(System.getProperty("user.dir") + "/copied.txt"),
				StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

		// 内存映射文件
		MappedByteBuffer inMappedBuf = inChannel.map(MapMode.READ_ONLY, 0, inChannel.size());
		MappedByteBuffer outMappedBuf = outChannel.map(MapMode.READ_WRITE, 0, inChannel.size());

		// 直接对缓冲区进行数据的读写操作
		byte[] dst = new byte[inMappedBuf.limit()];
		inMappedBuf.get(dst);
		outMappedBuf.put(dst);

		inChannel.close();
		outChannel.close();
	}

	// 通道之间的数据传输（直接缓冲区）
	public static void testTransfer() throws IOException {
		FileChannel inChannel = FileChannel.open(Paths.get(System.getProperty("user.dir") + "/coding.txt"),
				StandardOpenOption.READ);
		FileChannel outChannel = FileChannel.open(Paths.get(System.getProperty("user.dir") + "/copied.txt"),
				StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

//		inChannel.transferTo(0, inChannel.size(), outChannel);
		outChannel.transferFrom(inChannel, 0, inChannel.size());

		inChannel.close();
		outChannel.close();
	}

	// 分散和聚集
	public static void testScatterAndGather() throws IOException {
		RandomAccessFile raf1 = new RandomAccessFile(System.getProperty("user.dir") + "/coding.txt", "rw");
		// 获取通道
		FileChannel channel1 = raf1.getChannel();
		// 分配指定大小的缓冲区
		ByteBuffer buf1 = ByteBuffer.allocate(100);
		ByteBuffer buf2 = ByteBuffer.allocate(1024);

		ByteBuffer[] dst = { buf1, buf2 };

		// 分散读取
		channel1.read(dst);

		for (ByteBuffer buf : dst) {
			buf.flip();
		}

		System.out.println("----------Scattering Reads----------");
		System.out.println("Buffer1: " + new String(dst[0].array(), 0, dst[0].limit()));
		System.out.println("Buffer2: " + new String(dst[1].array(), 0, dst[1].limit()));

		RandomAccessFile raf2 = new RandomAccessFile(System.getProperty("user.dir") + "/coding.txt", "rw");
		FileChannel channel2 = raf2.getChannel();

		// 聚集写入
		channel2.write(dst);
		
		raf1.close();
		raf2.close();
	}

	// 获取支持的字符集
	public static void testAvailableCharsets() {
		Map<String, Charset> map = Charset.availableCharsets();
		Set<Entry<String, Charset>> set = map.entrySet();
		for (Entry<String, Charset> entry : set) {
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
	}

	// 字符集编码器与解码器
	public static void testCharsetEncoderAndDecoder() throws IOException {
		Charset cs1 = Charset.forName("GBK");

		// 获取编码器
		CharsetEncoder ce = cs1.newEncoder();

		// 获取解码器
		CharsetDecoder cd = cs1.newDecoder();

		CharBuffer cBuf = CharBuffer.allocate(1024);
		String str = "Java从入门到放弃";
		cBuf.put(str);
		cBuf.flip();

		// 编码
		ByteBuffer bBuf = ce.encode(cBuf);
		for (int i = 0; i < getLength(str); i++) {
			System.out.println("encoding: " + bBuf.get());
		}

		// 解码
		bBuf.flip();
		CharBuffer cBuf2 = cd.decode(bBuf);
		System.out.println("GBK decoding: " + cBuf2.toString());
		
		Charset cs2 = Charset.forName("UTF-8");
		bBuf.flip();
		CharBuffer cBuf3 = cs2.decode(bBuf);
		System.out.println("UTF-8 decoding: " + cBuf3.toString());
	}

	/**
	 * 得到一个字符串的字节长度
	 * 
	 * @param String s 需要得到长度的字符串
	 * @return int 得到的字符串长度
	 */
	public static int getLength(String s) {
		int valueLength = 0;
		String chinese = "[\u4e00-\u9fa5]";
		// 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
		for (int i = 0; i < s.length(); i++) {
			// 获取一个字符
			String temp = s.substring(i, i + 1);
			// 判断是否为中文字符
			if (temp.matches(chinese)) {
				// 中文字符长度为2
				valueLength += 2;
			} else {
				// 其他字符长度为1
				valueLength += 1;
			}
		}
		// 进位取整
		return valueLength;
	}

}
