package stuct.link;

/**
 * desc  : 链表实现队列
 *          添加  O(n)
 *          查看队头 删除 O(1)
 *
 * author: stone
 * email : aa86799@163.com
 * time  : 2018/12/25 10 50
 */
public class MyLinkedListQueue<E> implements IQueue<E> {

    public static void main(String[] args) {
        MyLinkedListQueue<String> queue = new MyLinkedListQueue<>();
        for (int i = 0; i < 4; i++) {
            queue.enqueue(i+"item");
        }
        System.out.println(queue);

        queue.enqueue("111");
        queue.enqueue("222");
        System.out.println(queue);

        queue.dequeue();
        System.out.println(queue);

        System.out.println(queue.getFront());
    }

    private MyLinkedList2<E> linkedList;

    public MyLinkedListQueue() {
        linkedList = new MyLinkedList2<>();
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        linkedList.addLast(e);
    }

    @Override
    public E dequeue() {
        return linkedList.removeFirst();
    }

    @Override
    public E getFront() {
        return linkedList.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("queue size: %d,  \n", getSize()));
        sb.append("front[");
        sb.append(linkedList);
        sb.append("]tail\n");
        return sb.toString();
    }
}
