package example.study;

/**
 * 静态代理模式
 */

public class DelegateDemo {
    public static void main(String[] args) {
        Man person = new Man("小明");
//        Woman person = new Woman("小红");
        Proxy p = new Proxy(person) {
            @Override
            public void before() {
                System.out.println("购物前");
            }

            @Override
            public void after() {
                System.out.println("购物后");
            }
        };
        p.shopping();
    }
}

interface Subject {
    public void shopping();
}

class Man implements Subject {
    private String name;
    public Man(String name) {
    	this.name = name;
    }
    public void shopping() {
        System.out.println("Man: " + name);
    }
}

class Woman implements Subject {
    private String name;
    public Woman(String name) {
		this.name = name;
	}
    public void shopping() {
        System.out.println("Woman: " + name);
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
