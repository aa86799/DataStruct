package com.stone.stuct.link;

/**
 * desc  : 链表 使用虚拟头节点(dummy head node)
 *          增：O(n)
 *          删除: O(n)
 *          查: O(n)
 *          这三者若只操作链表头，则是 O(1)
 * author: stone
 * email : aa86799@163.com
 * time  : 2018/12/24 12 39
 */
public class MyLinkedList2<E> {

    public static void main(String[] args) {
        MyLinkedList2<Integer> linkedList = new MyLinkedList2<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i*20);
        }
        System.out.println(linkedList);

        System.out.println("-----add-----");
        linkedList.add(3, 33);
        linkedList.addLast( 77);
        System.out.println(linkedList);

        System.out.println("-----remove-----");
        linkedList.remove(1);
        System.out.println(linkedList);
        linkedList.removeFirst();
        System.out.println(linkedList);
        linkedList.removeLast();
        System.out.println(linkedList);

        System.out.println("-----get-----");
        System.out.println(linkedList.get(2));
        System.out.println(linkedList.getFirst());
        System.out.println(linkedList.getLast());
    }

//    private Node head;
    private int size;
    private Node dummyHead;

    public MyLinkedList2() {
//        head = null;
        size = 0;
        dummyHead = new Node();
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

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
//        return head == null;
        return size == 0;
    }

    public E get(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("get failed");
        }

        Node cur = dummyHead;
        for (int i = 0; i <= index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("linked list add error.");
        }

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        prev.next = new Node(e, prev.next);
        size++;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    public boolean contains(E e) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("remove failed.");
        }
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;
        size--;
        return retNode.e;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("linked list size: %d,  \n", getSize()));
        sb.append("[");
        Node p = dummyHead.next;
        /*for (int i = 0; i < size; i++) {
            sb.append(p);
            if (i != size - 1) {
                sb.append(",");
            }
            p = p.next;
        }*/
        while (p != null) {
            sb.append(p.e + "->");
            p = p.next;
        }
        sb.append("null");

        sb.append("]");
        return sb.toString();
    }

}
