package example.study;

import java.util.Arrays;
import java.util.Random;

/**
 * Math、Random、Arrays工具类
 */

public class MathRandomArraysDemo {

	public static void main(String[] args) {
		// 四舍五入整数
		System.out.println(Math.round(3.54235));
		// 四舍五入保留两位小数
		System.out.println(Math.round(3.54235*100)/100.00);
		// 0-1随机数（不包括1，double类型）
		System.out.println(Math.random());
		// 圆周率
		System.out.println(Math.PI);
		
		System.out.println();
		
		// 伪随机数
		Random r = new Random();
		// 0-99随机整数
		System.out.println(r.nextInt(100));
		
		// 数组操作工具类
		int[] nums = {1, 2, 3, 23, 54, 6};
		printArray(nums);
		
		nums = Arrays.copyOf(nums, 10);
		printArray(nums);
		
		Arrays.sort(nums);
		printArray(nums);
		
		int[] temp = new int[nums.length];
		System.arraycopy(nums, 0, temp, 0, temp.length);
		printArray(temp);
		
		// 二分查找算法（查找速度快，必须要求数组是有序的）
		int index = Arrays.binarySearch(nums, 6);
		System.out.println("index: " + index);
	}

	public static void printArray(int[] array) {
		StringBuilder sb = new StringBuilder();
		sb.append("array: {");
		for (int i : array) {
			sb.append(i);
			sb.append(", ");
		}
		sb.replace(sb.length() - 2, sb.length(), "");
		sb.append("}");
		System.out.println(sb.toString());
	}
}
