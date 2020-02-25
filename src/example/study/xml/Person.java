package example.study.xml;

public class Person {
	private String id;
	private String name;
	private int age;
	private String sex;
	private String tel;
	
	public Person() {
	}
	
	public Person(String id, String name, int age, String sex, String tel) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.tel = tel;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + ", sex=" + sex + ", tel=" + tel + "]";
	}
}
