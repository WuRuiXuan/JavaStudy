package example.study;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuruixuan on 2018/1/3.
 */

public class ListDemo {
    public static void main(String[] args) {

    }

    public void arrayList() {
        List list = new ArrayList();
        list.add(10);
        list.add(20);
        list.add(30);
        System.out.println(list.size());

        int item0 = (int) list.get(0);

    }
}
