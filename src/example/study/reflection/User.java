package example.study.reflection;

public class User {
	private String name;
	private int nums;
	
	public User() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNums() {
		return nums;
	}

	public void setNums(int nums) {
		this.nums = nums;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", nums=" + nums + "]";
	}
}
