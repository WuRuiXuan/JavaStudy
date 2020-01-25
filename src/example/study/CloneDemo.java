package example.study;

/**
 * 对象的克隆
 */

public class CloneDemo {
    public static void main(String[] args) {
        Dog2 dog = new Dog2("jack", 4);
        try {
            Dog2 clonedDog = (Dog2) dog.clone();
            System.out.println("clonedDog: " + clonedDog.name + " " + clonedDog.age);
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}

class Dog2 implements Cloneable {
    public String name;
    public int age;

    public Dog2(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

