package example.study;

/**
 * Created by wuruixuan on 2018/1/2.
 */

public class CloneDemo {
    public static void main(String[] args) {
        Dog2 dog = new Dog2("jack", 4);
        try {
            Dog2 dog_clone = (Dog2) dog.clone();
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

