package example.study;

import java.util.ArrayList;
import java.util.List;

/**
 * ArrayList
 */

public class ListDemo {
    public static void main(String[] args) {
    	arrayList();
    }

    public static void arrayList() {
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        System.out.println("size: " + list.size());

        int item0 = (int) list.get(0);
        System.out.println("item0: " + item0);
    }
}
