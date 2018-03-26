package example.study;

/**
 * Created by wuruixuan on 2017/12/26.
 */

public class Delegate {
    public static void main(String[] args) {
        Man man = new Man();
        Woman woman = new Woman();
        Proxy p = new Proxy(man) {
            @Override
            public void before() {
                System.out.println("购物前");
            }

            @Override
            public void after() {
                System.out.println("购物后");
            }
        };
    }
}

interface Subject {
    public void shopping();
}

class Man implements Subject {
    private String name;
    public void shopping() {
        System.out.println();
    }
}

class Woman implements Subject {
    private String name;
    public void shopping() {
        System.out.println();
    }
}

abstract class Proxy implements Subject {
    public Subject target;
    abstract public void before();
    abstract public void after();
    public Proxy(Subject target) {
        this.target = target;
    }
    public void shopping() {
        before();
        target.shopping();
        after();
    }
}
