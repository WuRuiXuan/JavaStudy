package example.study;

/**
 * 单例模式
 */

public class SingleDemo {
    public static void main(String[] args) {
        SingleClass s = SingleClass.getInstance();
        s.method();
    }
}

class SingleClass {
    private static SingleClass singleClass = new SingleClass();
    private SingleClass() {};//构造方法私有化
    public static SingleClass getInstance() {
        return singleClass;
    }
    public void method() {
    	System.out.println("单例私有方法");
    };
}