package example.study;

/**
 * Created by wuruixuan on 2017/12/26.
 */

public class Adapter {
    public static void main(String[] args) {
        PowerA a = new PowerImpA();
        input(a);

        PowerB b = new PowerImpB();
        PowerB_Adapter e = new PowerB_Adapter(b);
        input(e);
    }

    public static void input(PowerA a) {
        a.connectA();
    }
}


class PowerB_Adapter implements PowerA {
    private PowerB b;
    public PowerB_Adapter(PowerB b) {
        this.b = b;
    }
    public void connectA() {
        b.connectB();
    }
}

interface PowerA {
    public void connectA();
}

interface PowerB {
    public void connectB();
}

class PowerImpA implements PowerA {
    public void connectA() {
        System.out.println("power A!");
    }
}

class PowerImpB implements PowerB {
    public void connectB() {
        System.out.println("power B!");
    }
}