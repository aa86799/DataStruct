package algorithm;


import java.util.*;

public class Test {

    private static List<Integer> get(List<Integer> list, int dstValue, List<Integer> remainList) {
        int sum = 0;

        Random random = new Random();
        for (int i = 1; i <= 14; i++) {
            int ranIndex = random.nextInt(remainList.size());
            if (list.contains(ranIndex)) {
                continue;
            }
            if (remainList.get(ranIndex) == dstValue) {
                continue;
            }
            sum += remainList.get(ranIndex);
            list.add(remainList.get(ranIndex));

            if (sum == dstValue) {
                break;
            }
            if (sum > dstValue) {
                i = 1;
                sum = 0;
                list.clear();
            }
        }

        return list;
    }

    private static void m1() {
        long startTime = System.nanoTime();

        List<Integer> list = new ArrayList<>(14);
        LinkedList<Integer> remainList = new LinkedList<>();
        for (int i = 1; i <= 100; i++) {
            remainList.add(i);
        }
        get(list, 100, remainList);

        System.out.println(Arrays.toString(list.toArray()));

        System.out.println("avl total time:" + ((System.nanoTime() - startTime) / 1.0e9));

    }



    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            m1();
        }
    }
}
