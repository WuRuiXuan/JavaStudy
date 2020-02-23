package example.study;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;

/**
 * 注解
 */

public class AnnotationDemo {

	public static void main(String[] args) {
		Person p = new Person("jack");
		p.print();
		
		Class personClass = Person.class;
		MyAnnotation ma = (MyAnnotation) personClass.getAnnotation(MyAnnotation.class);
		String name = ma.name();
		String info = ma.info();
		
		try {
			Person p1 = (Person) personClass.newInstance();
			p1.setName(name);
			p1.setInfo(info);
			System.out.println(p1);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@MyAnnotation(name="lucy") // 有默认值在使用时可以不设值
	static class Person {
		private String name;
		private String info;
		
		@SuppressWarnings({ "rawtypes", "unused" }) // 表示关闭不当的编译器警告信息
		private ArrayList list;
		
		public Person() {
		}

		public Person(String name) {
			super();
			this.name = name;
		}
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getInfo() {
			return info;
		}

		public void setInfo(String info) {
			this.info = info;
		}

		@Override
		public String toString() {
			return "Person [name=" + name + ", info=" + info + "]";
		}

		@Deprecated // 表示该方法已过时，有新方法替代它
		public void print() {
			System.out.println(name);
		}
		
//		@Documented // 表示生成doc文档的时候添加注解
//		@Target // 表示注解在哪些类型上可以使用
//		@Inherited // 表示注解是否允许被子类继承
	}
}


/**
 * 注解的作用范围：
 * SOURCE-只在源程序中存在，编译后不存在
 * CLASS-编译成Class文件后注解还是存在
 * RUNTIME-运行时注解还是存在（如需要赋值）
 */
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation { //自定义注解
	// 定义变量
	public String name();
	public String info() default "javaman";
}
