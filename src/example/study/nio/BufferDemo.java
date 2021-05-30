package example.study.nio;

import java.nio.ByteBuffer;

/**
 * 缓冲区（Buffer）：缓冲区就是数组，用于存储不同类型的数据
 *
 */
public class BufferDemo {

	public static void main(String[] args) {
//		testBuffer();
//		testMark();
		testDirect();
	}
	
	// 缓冲区的四个核心属性
	public static void testBuffer() {
		String str = "abcde";
		
		// 分配一个指定大小的缓冲区
		ByteBuffer buf = ByteBuffer.allocate(1024);
		
		System.out.println("----------allocate()----------");
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		
		// 存入缓冲区
		buf.put(str.getBytes());
		
		System.out.println("----------put()----------");
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		
		// 切换到读取模式
		buf.flip();
		
		System.out.println("----------flip()----------");
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		
		// 读取缓冲区中的数据
		byte[] dst = new byte[buf.limit()];
		buf.get(dst);
		
		System.out.println("----------get()----------");
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		System.out.println(new String(dst));
		
		// 重新读取数据
		buf.rewind();
		
		System.out.println("----------rewind()----------");
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		
		// 清空缓冲区，但缓冲区中的数据依然存在，只是处于“被遗忘”状态
		buf.clear();
		
		System.out.println("----------clear()----------");
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		System.out.println((char) buf.get());
	}
	
	// 标记
	public static void testMark() {
		String str = "abcde";
		ByteBuffer buf = ByteBuffer.allocate(1024);
		buf.put(str.getBytes());
		buf.flip();
		byte[] dst = new byte[buf.limit()];
		buf.get(dst, 0, 2);
		System.out.println(new String(dst, 0, 2));
		System.out.println("position: " + buf.position());
		
		// 标记
		buf.mark();
		
		buf.get(dst, 2, 2);
		System.out.println(new String(dst, 2, 2));
		System.out.println("position: " + buf.position());
		
		// 恢复到标记的位置
		buf.reset();
		
		System.out.println("after reset position: " + buf.position());
		
		// 判断缓冲区中是否还有剩余的数据
		if (buf.hasRemaining()) {
			// 获取缓冲区中可以操作的数量
			System.out.println("remaining: " + buf.remaining());
		}
	}

	// 直接缓冲区与非直接缓冲区
	public static void testDirect() {
		ByteBuffer buf = ByteBuffer.allocateDirect(1024);
		System.out.println(buf.isDirect());
	}
	
}
