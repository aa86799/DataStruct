package stuct.link;

/**
 * desc  : 链表实现队列
 *          添加  O(n)
 *          查看队头 删除 O(1)
 *
 *          为了改进 添加操作， 记录一个链表尾的节点(指针)，这样添加也就成了 O(1)
 *          即有 Node-head Node-tail
 * author: stone
 * email : aa86799@163.com
 * time  : 2018/12/25 10 50
 */
public class MyLinkedListQueue2<E> implements IQueue<E> {

    public static void main(String[] args) {
        MyLinkedListQueue2<String> queue = new MyLinkedListQueue2<>();
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

    private Node head, tail;
    private int size;
    public MyLinkedListQueue2() {
        size = 0;
    }

    private class Node {
        E e;
        Node next;

        Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        Node(E e) {
            this(e, null);
        }

        Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E e) {
        if (tail == null) {
            head = tail = new Node(e);
        } else {
            tail.next = new Node(e);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("dequeue failed.");
        }
        Node ret = head;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return ret.e;
    }

    @Override
    public E getFront() {
        /*if (head != null)
            return head.e;
        return null;*/
        if (isEmpty()) {
            throw new IllegalArgumentException("dequeue failed.");
        }
        return head.e;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("queue size: %d,  \n", getSize()));
        sb.append("front[");
        Node cur = head;
        while (cur != null) {
            sb.append(cur.e + "->");
            cur = cur.next;
        }
        sb.append("null");
        sb.append("]tail\n");
        return sb.toString();
    }
}
