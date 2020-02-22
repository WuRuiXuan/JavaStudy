package example.study;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Set;

/**
 * 枚举
 */

public class EnumDemo {

	public static void main(String[] args) {
		Color c = Color.RED;
		System.out.println("name: " + c.name());
		System.out.println("ordinal: " + c.ordinal()); // 序号
		
		System.out.println();
		
		// 遍历
		Color[] cs = Color.values();
		for (Color color : cs) {
			System.out.println(color);
		}
		
		System.out.println();
		
		EnumMap<Color, String> map = new EnumMap<>(Color.class);
		map.put(Color.RED, "red");
		map.put(Color.GREEN, "green");
		map.put(Color.BLUE, "blue");
		
		Set<Color> set = map.keySet();
		for (Color color : set) {
			System.out.println(color + "--" + map.get(color));
		}
		
		System.out.println();
		
		EnumSet<Color> enumSet = EnumSet.allOf(Color.class);
		for (Color color : enumSet) {
			System.out.println(color);
		}
		
		System.out.println();
		
		System.out.println("getColor: " + Color.RED.getColor());
		System.out.println("color: " + Color.RED.color());
	}

	enum Color implements Info { // 隐式的继承了Enum类
//		RED, GREEN, BLUE; // public static final
//		RED(1), GREEN(2), BLUE(3);
		RED(1) {
			@Override
			public String color() {
				return "红色";
			}
		},
		GREEN(2) {
			@Override
			public String color() {
				return "绿色";
			}
		},
		BLUE(3) {
			@Override
			public String color() {
				return "蓝色";
			}
		};
		
		private Color() { // 构造方法只能是私有的
			System.out.println("默认的构造方法");
		}
		
		private int color;
		private Color(int color) {
			this.color = color;
			System.out.println("带参数的构造方法");
		}
		
		@Override
		public int getColor() {
			return color;
		}
		
		public abstract String color();
	}
	
	interface Info {
		public int getColor();
	}
	
	enum ColorUtils { // 单例
		color;
	}
}
