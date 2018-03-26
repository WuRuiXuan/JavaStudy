package example.study;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by wuruixuan on 2018/1/1.
 */

public class ComparableDemo {
    public static void main(String[] args) {
        Cat[] cats = new Cat[]{new Cat("tom", 2), new Cat("jerry", 3), new Cat("huahua", 5)};
        Arrays.sort(cats);

        Dog[] dogs = new Dog[]{new Dog("tom", 2), new Dog("jerry", 3), new Dog("huahua", 5)};
        Arrays.sort(dogs, new DogComparator());
    }
}

class Cat implements Comparable<Cat> {

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
    @Override
    public int compare(Dog d1, Dog d2) {
        if (d1.age < d2.age) {
            return -1;
        } else if (d1.age > d2.age) {
            return 1;
        } else {
            return 0;
        }
    }
}