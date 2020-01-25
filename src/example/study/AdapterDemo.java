package example.study;

/**
 * 适配器模式
 */

public class AdapterDemo {
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