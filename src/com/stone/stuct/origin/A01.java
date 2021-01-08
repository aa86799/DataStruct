package com.stone.stuct.origin;

/**
 * desc  :
 * author: stone
 * email : aa86799@163.com
 * time  : 2018/4/11 16 14
 */
public class A01<T> {
    class Link<T> {//链结点
        T data;     //数据域
        Link<T> next; //后继指针，结点           链域
        Link<T> previous; //前驱指针，结点       链域
        Link(T data) {
            this.data = data;
        }
        void displayLink() {
            System.out.println("the data is " + data.toString());
        }
    }

    private Link<T> head;     //首结点
    private Link<T> rear;     //尾部指针

    public boolean isEmpty() {
        return head == null;
    }

    public void displayList4Head() {//从头开始遍历
        System.out.println("List (first-->last):");
        Link<T> current = head;
        while (current != null) {
            current.displayLink();
            current = current.next;
        }
    }

    public void insertFirst(T data) {// 插入 到 链头
        Link<T> newLink = new Link<T>(data);
        if (isEmpty()) {//为空时，第1次插入的新结点为尾结点
            rear = newLink;
        } else {
            head.previous = newLink; //旧头结点的上结点等于新结点
        }
        newLink.next = head; //新结点的下结点旧头结点
        head = newLink; //赋值后，头结点的下结点是旧头结点，上结点null
    }

    public boolean insertAfter(T key, T data) {//插入在key之后, key不存在return false
        if (isEmpty()) {
            return false;
        }
        Link<T> current = head;
        while (!current.data.equals(key)) {
            current = current.next;
            if (current == null) {//一直遍历到尾时 为null
                return false;
            }
        }
        Link<T> newLink = new Link<T>(data);
        if (current == rear) {
            rear = newLink;
        } else {
            newLink.next = current.next;
            current.next.previous = newLink;
        }
        current.next = newLink;
        newLink.previous = current;
        return true;
    }

    public static void main(String[] args) {
        A01<Integer> m = new A01<>();
        m.insertFirst(8);
        m.insertFirst(3);
        m.insertFirst(5);
        m.insertFirst(7);
        m.insertFirst(2);
        m.displayList4Head();

        m.insertAfter(5, 4);
        m.displayList4Head();
    }
}
