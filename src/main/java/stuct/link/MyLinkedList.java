package stuct.link;

/**
 * desc  :
 * author: stone
 * email : aa86799@163.com
 * time  : 2018/12/24 12 39
 */
public class MyLinkedList<E> {

    public static void main(String[] args) {
        MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i*20);
        }
        System.out.println(linkedList);

        linkedList.add(3, 33);
//        linkedList.add(3, 33);
        System.out.println(linkedList);
    }

    private Node head;
    private int size;

    public MyLinkedList() {
        head = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
//        return head == null;
        return size == 0;
    }

    public void addFirst(E e) {
//        Node node = new Node(e);
//        node.next = head;
//        head = node;
//        size++;

        head = new Node(e, head);
        size++;
    }

    public void addLast(E e) {

    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("linked list add error.");
        }

        if (index == 0) {
            addFirst(e);
        } else {
            Node prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
//            Node node = new Node(e);
//            node.next = prev.next;
//            prev.next = node;

            prev.next = new Node(e, prev.next);
            size++;
        }
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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("linked list size: %d,  queue: \n", getSize()));
        sb.append("[");
//        for (int i = front; i != tail; i = (i + 1) % data.length) {
//            sb.append(data[i]);
//            if ((i + 1) % data.length != tail) {
//                sb.append(", ");
//            }
//        }
        Node p = head;
        for (int i = 0; i < size; i++) {
            sb.append(p);
            if (i != size - 1) {
                sb.append(",");
            }
            p = p.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
