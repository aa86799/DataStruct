package stuct.origin;

import java.util.HashSet;

/**
 * desc  :
 * author: stone
 * email : aa86799@163.com
 * time  : 2018/9/11 20 43
 */
public class TestStruct {

    public static void main(String[] args) {

        testHashSet();
    }

    private static void testHashMap() {

    }

    private static void testHashSet() {
        HashSet set = new HashSet();
        set.add(2);
        set.add("3");
        set.add(1);
        set.add(1);
        set.add("77");
        set.add(8);

        for (Object object : set) {
            System.out.println(object);
        }
    }
}
