package stuct.link;

/**
 * desc  :
 * author: stone
 * email : aa86799@163.com
 * time  : 2018/12/25 11 24
 */
public class TestTime {

    public static void main(String[] args) {
        MyLinkedListQueue<Integer> queue1 = new MyLinkedListQueue<>();
        System.out.println("time1:" + testRunTime(queue1));

        MyLinkedListQueue2<Integer> queue2 = new MyLinkedListQueue2<>(); //高效
        System.out.println("time2:" + testRunTime(queue2));
    }

    private static double testRunTime(IQueue<Integer> queue) {
        long startTime = System.nanoTime();

        int count = 1000*100;
        //添加
        for (int i = 0; i < count; i++) {
            queue.enqueue(i);
        }

        //删除
        for (int i = 0; i < count / 3; i++) {
            queue.dequeue();
        }

        return (System.nanoTime() - startTime) / 1.0e9;
    }
}
