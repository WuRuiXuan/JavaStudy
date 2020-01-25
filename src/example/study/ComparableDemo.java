package example.study;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 对象比较器
 */

public class ComparableDemo {
	public static void main(String[] args) {
		Cat[] cats = new Cat[] { new Cat("huahua", 5), new Cat("tom", 2), new Cat("jerry", 3) };
		Arrays.sort(cats);
		System.out.print("cats: ");
		for (Cat cat : cats)
			System.out.print(cat.name + " " + cat.age + " ");
		System.out.print("\n");

		System.out.print("dogs: ");
		Dog[] dogs = new Dog[] { new Dog("huahua", 5), new Dog("tom", 2), new Dog("jerry", 3) };
		Arrays.sort(dogs, new DogComparator());
		for (Dog dog : dogs)
			System.out.print(dog.name + " " + dog.age + " ");
		System.out.print("\n");
	}
}

class Cat implements Comparable<Cat> {
	// 按年龄从小到大排序
	@Override
	public int compareTo(Cat cat) {
		if (this.age < cat.age) {
			return -1;
		} else if (this.age > cat.age) {
			return 1;
		} else {
			return 0;
		}
	}

	public String name;
	public int age;

	public Cat(String name, int age) {
		this.name = name;
		this.age = age;
	}
}

class Dog {
	public String name;
	public int age;

	public Dog(String name, int age) {
		this.name = name;
		this.age = age;
	}
}

class DogComparator implements Comparator<Dog> {
	// 按年龄从大到小排序
	@Override
	public int compare(Dog d1, Dog d2) {
		if (d1.age < d2.age) {
			return 1;
		} else if (d1.age > d2.age) {
			return -1;
		} else {
			return 0;
		}
	}
}