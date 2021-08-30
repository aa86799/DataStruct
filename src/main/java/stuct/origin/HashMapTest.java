package stuct.origin;

import java.util.HashMap;
import java.util.Map;

/**
 * desc  :
 * author: stone
 * email : aa86799@163.com
 * time  : 2018/9/16 15 58
 */
public class HashMapTest {
    public static void main(String[] args) {

        HashMap<Integer, Integer> map = new HashMap<>(6, 0.75f);
        int threshold = tableSizeFor(9);
        System.out.println("threshold: " + threshold);
        for (int i = 0; i < 20; i++) {
            map.put(1+ i, i);
        }
        for (HashMap.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey());
        }

        System.out.println();
        System.out.println(1 << 5);

    }

    static final int tableSizeFor(int cap) {
        int MAXIMUM_CAPACITY = 1 << 30;
        int n = cap - 1;
        System.out.println("step: " + n);
        n |= n >>> 1;
        System.out.println("step: " + n);
        n |= n >>> 2;
        System.out.println("step: " + n);
        n |= n >>> 4;
        System.out.println("step: " + n);
        n |= n >>> 8;
        System.out.println("step: " + n);
        n |= n >>> 16;
        System.out.println("step: " + n);
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
