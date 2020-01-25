package example.study;

/**
 * 简单工厂模式
 */

public class FactoryDemo {
    public static void main(String[] args) {
        Work work = FactoryExample.getWork("tv");
        if (work != null) {
            work.working();
        }
    }
}

class FactoryExample {
    public static Work getWork(String product) {
        if (product.equals("phone")) {
            return new TCLphone();
        }
        else if (product.equals("tv")) {
            return new TCLtv();
        }
        else {
            return null;
        }
    }
}

interface Work {
    public void working();
}

class TCLphone implements Work {
    public void working() {
    	System.out.println("TCLphone working!");
    }
}

class TCLtv implements Work {
    public void working() {
    	System.out.println("TCLtv working!");
    }
}