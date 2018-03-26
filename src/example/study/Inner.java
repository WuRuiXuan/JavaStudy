package example.study;

/**
 * Created by wuruixuan on 2017/12/26.
 */

public class Inner {
    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass();
        outerClass.print();

        OuterClass.InnerClass innerClass = outerClass.new InnerClass("成员内部类");
        innerClass.print();

        OuterClass.StaticInnerClass staticInnerClass = new OuterClass.StaticInnerClass();

        outerClass.print3();

        outerClass.print4(new Child() {
            @Override
            public void desc() {

            }
        });
    }
}

class OuterClass {
    public void print() {
        InnerClass innerClass = new InnerClass("成员内部类");
        innerClass.print();
    }
    // 成员内部类
    class InnerClass {
        private String name;
        public InnerClass(String name) {
            this.name = name;
        }
        public void print() {
            System.out.println(name);
        }
    }
    // 方法内部类
    public void print2() {
        final int x = 10;
        class InnerClass2 {
            public void print() {
                System.out.println("在方法中定义内部类" + x);
            }
        }
        InnerClass2 innerClass2 = new InnerClass2();
        innerClass2.print();
    }
    // 静态内部类
    static class StaticInnerClass {

    }

    public void print3() {
        // 匿名内部类
        Child c = new Child() {
            @Override
            public void desc() {

            }
        };
        c.desc();
    }

    public void print4(Child c) {

    }
}

interface Child {
    public void desc();
}
