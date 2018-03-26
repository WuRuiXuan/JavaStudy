package example.study;

/**
 * Created by wuruixuan on 2017/12/26.
 */

public class Single {
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
    public void method() {};
}