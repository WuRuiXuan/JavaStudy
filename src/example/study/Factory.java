package example.study;

/**
 * Created by wuruixuan on 2017/12/26.
 */

public class Factory {
    public static void main(String[] args) {
        Work work = Factory_Example.getWork("tv");
        if (work != null) {
            work.working();
        }
    }
}

class Factory_Example {
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

    }
}

class TCLtv implements Work {
    public void working() {

    }
}