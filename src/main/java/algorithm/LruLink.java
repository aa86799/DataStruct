package algorithm;


import java.util.Random;

/**
 * desc:
 * author:  stone
 * email:   aa86799@163.com
 * blog :   https://stone.blog.csdn.net
 * time:    2020/1/21 00:41
 */

/*
 * 维护一个有序单链表，越靠近链表尾部的结点是越早之前访问的。当有一个新的数据被访问时，我们从链表头开始顺序遍历链表。
 * 1. 如果此数据之前已经被缓存在链表中了，遍历得到这个数据对应的结点，并将其从原来的位置删除，然后再插入到链表的头部。
 * 2. 如果此数据没有在缓存链表中，又可以分为两种情况：
 *      如果此时缓存未满，则将此结点直接插入到链表的头部；
 *      如果此时缓存已满，则链表尾结点删除，将新的数据结点插入链表的头部。
 *      这样我们就用链表实现了一个 LRU 缓存，是不是很简单？现在我们来看下 缓存访问的时间复杂度是多少。
 *      因为不管缓存有没有满，我们都需要遍历一遍链表，所以这种基于链表的实现思路，缓存访问的时间复杂度为 O(n)。
 *
 * 实际上，我们可以继续优化这个实现思路，比如引入散列表（Hash table）来记录每个数据的位置，将缓存访问的时间复杂度降到 O(1)。
 * 因为要涉及我们还没有讲到的数据结构，所以这个优化方案，我现在就不详细说了，等讲到散列表的时候，我会再拿出来讲。
 * 除了基于链表的实现思路，实际上还可以用数组来实现 LRU 缓存淘汰策略。如何利用数组实现 LRU 缓存淘汰策略呢？我把这个问题留给你思考。
 */

public class LruLink<E> {

    private final int total = 3; //总容量
    private int size; //当前容量
    private Node<E> head;

    public static void main(String[] args) {
        LruLink<Integer> lruLink = new LruLink();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int v = random.nextInt(6);
            System.out.print("要添加的数据：" + v);
            lruLink.add(v);
        }
        System.out.println(lruLink.head);
        System.out.println(lruLink.size);
    }

    private void add(E e) {
        Node<E> node = find(e);
        if (node != null) {
            System.out.print(" 数据已存在");
            if (node.next != null) {
                System.out.print(" 是非尾结点");
                node.e = node.next.e;
                node.next = node.next.next;
                head = new Node<>(e, head);
            } else {
                //表示在尾结点，要删除尾结点，再插入到头部
                System.out.print(" 是尾结点");
                deleteLast();
                head = new Node<>(e, head);
                size++;
            }
            System.out.println();
        } else {
            System.out.print(" 数据未存在");
            if (size >= total) {
                System.out.println(" 容量已满");
                deleteLast();
            } else {
                System.out.println(" 容量未满");
            }
            size++;
            head = new Node<>(e, head);
        }
        System.out.println("add尾结点后：" + head);
    }

    private void deleteLast() {
        Node<E> p = head;
        if (p.next == null) {
            head = null;
        } else {
            while (p.next.next != null) {
                p = p.next;
            }
            p.next = null;
        }
        size--;
        System.out.println(" 删除尾结点后：" + head);
    }

    private Node<E> find(E e) {
        Node<E> p = head;
        while (p != null) {
            if (p.e != null && p.e.equals(e)) {
                return p;
            }
            p = p.next;
        }
        return null;
    }

    private static class Node<E> {
        E e;
        Node<E> next;
        Node(E e, Node<E> next) {
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
            return "Node{" +
                    "e=" + e +
                    ", next=" + next +
                    '}';
        }
    }

}

