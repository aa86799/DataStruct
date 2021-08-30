package stuct.queue;


/**
 * desc  : 优先级队列。基于动态数组。优先级高的放在队首或队尾
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/3/11 11 13
 */
public class PriorityQueue1<E extends Comparable<E>> implements IQueue<E> {

    public static void main(String[] args) {
        PriorityQueue1<Integer> queue = new PriorityQueue1<>();
        queue.enqueue(1);
        queue.enqueue(1);
        queue.enqueue(3);
        queue.enqueue(8);
        queue.enqueue(8);
        queue.enqueue(10);
        queue.enqueue(12);
        queue.enqueue(9);

        queue.enqueue(2);
        queue.enqueue(2);
        queue.enqueue(11);
        queue.enqueue(7);
        queue.enqueue(12);
        queue.enqueue(10);
        queue.enqueue(10);
        queue.enqueue(9);
        queue.enqueue(11);

        System.out.println(queue);
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        System.out.println(queue);
        System.out.println("front: " + queue.getFront());
    }

    private MyDynamicGenericArray<E> array;

    public PriorityQueue1() {
        this.array = new MyDynamicGenericArray<>();
    }

    public PriorityQueue1(int capacity) {
        this.array = new MyDynamicGenericArray<>(capacity);
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        if (isEmpty()) {
            array.addFirst(e);
        } else {
            aesEnqueue(e);
//            descEnqueue(e);
        }
    }

    //升序排列
    private void aesEnqueue(E e) {
        if (e.compareTo(array.getLast()) >= 0) {
            array.addLast(e);
        } else/* if (e.compareTo(array.getLast()) < 0)*/ {
            int start = 1;
            int end = getSize() - 1;
            int mid;
            while (start <= end) {
                mid = (start + end) >>> 1;
                E midValue = array.get(mid);
                if (e.compareTo(midValue) > 0) {
                    start = mid + 1;
                } else if (e.compareTo(midValue) < 0) {
                    end = mid - 1;
                } else {
                    start = mid;
                    break;
                }
            }
            //经过上面二分，找出 start 就是要插入的位置
            array.add(start, e);
//                System.out.println("mid = " + mid);
//                System.out.println("start = " + start);
//                System.out.println("end = " + end);

        }
    }

    //降序排列
    private void descEnqueue(E e) {
        if (e.compareTo(array.getFirst()) >= 0) {
            array.addFirst(e);
        } else /*if (e.compareTo(array.getFirst()) < 0)*/ {
            int start = 1;
            int end = getSize() - 1;
            int mid;
            while (start <= end) {
                mid = (start + end) >>> 1;
                E midValue = array.get(mid);
                if (e.compareTo(midValue) > 0) {
                    end = mid - 1;
                } else if (e.compareTo(midValue) < 0) {
                    start = mid + 1;
                } else {
                    start = mid;
                    break;
                }
            }
            //经过上面二分，找出 start 就是要插入的位置
            array.add(start, e);
//                System.out.println("mid = " + mid);
//                System.out.println("start = " + start);
//                System.out.println("end = " + end);

        }
    }


    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public String toString() {
        return array.toString();
    }
}
