package example.study.reflection;

public class Cat {
	
	private String name;
	private int age;
	public String sex;
	
	// 默认构造方法一定要保留
	public Cat() {
		
	}
	
	public Cat(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	private void sound(String text) {
		System.out.println(text);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Cat [name=" + name + ", age=" + age + "]";
	}
}
