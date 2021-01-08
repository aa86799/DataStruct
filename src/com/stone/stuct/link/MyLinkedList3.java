package com.stone.stuct.link;


/**
 * desc  : 链表 使用虚拟头节点(dummy head node)， 且是双(向)链表
 *          增：O(n)
 *          删除: O(n)
 *          查: O(n)
 *          这三者若只操作链表头、尾，则是 O(1)
 *
 *          在 增、删、查时，用 nearlyNode():boolean 判断 最近的节点： true 靠近 head；false 靠近 tail；
 *          在 增、删时，
 *              要考虑新结点的前后节点;
 *              要增、删的节点的前节点的 后节点；
 *              要增、删的节点的后节点的 前节点。
 * author: stone
 * email : aa86799@163.com
 * time  : 2018/12/24 12 39
 */
public class MyLinkedList3<E> {

    public static void main(String[] args) {
        MyLinkedList3<Integer> linkedList = new MyLinkedList3<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i*20);
        }
        System.out.println(linkedList);
        System.out.println();

        System.out.println("------add------");
//        linkedList.add(2, 33);
        linkedList.add(3, 33);
        linkedList.addLast( 77);
        System.out.println(linkedList);
        linkedList.add( 5, 999);
        System.out.println(linkedList);
        System.out.println();

        System.out.println("-----remove-----");
        linkedList.remove(5);
        System.out.println(linkedList);
        linkedList.removeFirst();
        System.out.println(linkedList);
        linkedList.removeLast();
        System.out.println(linkedList);
        System.out.println();
//
        System.out.println("-----get-----");
        System.out.println(linkedList.get(2));
        System.out.println(linkedList.getFirst());
        System.out.println(linkedList.getLast());
        System.out.println();

        System.out.println("-----reverse recursion------");
        MyLinkedList3.Node tail = linkedList.dummyTail.prev;
        StringBuilder sb = new StringBuilder();
        sb.append("tail->");
        while (tail != linkedList.dummyHead) {
            sb.append(tail.e + "->" );
            tail = tail.prev;
        }
        sb.append("head");
        System.out.println(sb);
    }

    private int size;
    private Node dummyHead;
    private Node dummyTail;

    public MyLinkedList3() {
        size = 0;
        dummyHead = new Node();
        dummyTail = new Node();
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }

    private class Node {
        E e;
        Node next;
        Node prev;

        Node(E e, Node next, Node prev) {
            this.e = e;
            this.next = next;
            this.prev = prev;
        }

        Node(E e) {
            this(e, null, null);
        }

        Node() {
            this(null, null, null);
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

        Node cur;
        if (nearlyNode(index)) {
//            System.out.println("get nearly head, index=" + index);
            cur = dummyHead;
            for (int i = 0; i <= index; i++) {
                cur = cur.next;
            }
        } else {
//            System.out.println("get nearly tail, index=" + index);
            cur = dummyTail;
            for (int i = size - 1; i >= index; i--) {
                cur = cur.prev;
            }
        }

        return cur.e;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    //最近的节点： true 靠近 head；false 靠近 tail
    private boolean nearlyNode(int index) {
        return size - index >= index;
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("linked list add error.");
        }

        Node p;
        Node newNode;
        if (nearlyNode(index)) {
//            System.out.println("add nearly head, index=" + index);
            p = dummyHead;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
            newNode = new Node(e, p.next, p);
            p.next.prev = newNode;
            p.next = newNode;
        } else {
//            System.out.println("add nearly tail, index=" + index);
            p = dummyTail;
            //size=5；  index =4；  循环第一次， next=index4 , 循环第二次 next=index3;
            for (int i = size; i >= index; i--) {
                p = p.prev;
            }
            newNode = new Node(e, p.next, p);
            p.next.prev = newNode;
            p.next = newNode;
        }

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
            if (cur.e != null && cur.e.equals(e)) {
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

        Node retNode;
        if (nearlyNode(index)) {
//            System.out.println("remove nearly head, index=" + index);
            Node p = dummyHead;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
            retNode = p.next;
            p.next = retNode.next;
            retNode.next.prev = p;
//            retNode.next = null;//可以注释掉, java 有垃圾回收机制
//            retNode.prev = null;//可以注释掉, java 有垃圾回收机制
        } else {
//            System.out.println("remove nearly tail, index=" + index);
            Node p = dummyTail;
            for (int i = size - 1; i >= index; i--) {
                p = p.prev;
            }
            retNode = p;
            retNode.prev.next = p.next;
            retNode.next.prev = p.prev;
//            retNode.next = null;//可以注释掉, java 有垃圾回收机制
//            retNode.prev = null;//可以注释掉, java 有垃圾回收机制
        }

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
        sb.append("head->");
        Node p = dummyHead.next;

        //遍历方式一
        for (int i = 0; i < size; i++) {
            sb.append(p.e + "->");
            p = p.next;
        }
        //遍历方式二
        /*while (p != dummyTail) {
            sb.append(p.e + "->");
            p = p.next;
        }*/
        sb.append("tail");
        return sb.toString();
    }

}
