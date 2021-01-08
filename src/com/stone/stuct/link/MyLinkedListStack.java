package com.stone.stuct.link;

/**
 * desc  : 链表实现栈
 *          出栈 入栈 查看栈顶： O(1)
 * author: stone
 * email : aa86799@163.com
 * time  : 2018/12/25 10 40
 */
public class MyLinkedListStack<E> implements IStack<E> {

    public static void main(String[] args) {
        MyLinkedListStack<Object> stack = new MyLinkedListStack<>();
        for (int i = 0; i < 4; i++) {
            stack.push(i+"-item");
        }
        System.out.println(stack);

        stack.push(99);
        stack.push(100);
        System.out.println(stack);

        stack.pop();
        System.out.println(stack);

        System.out.println("peek: " + stack.peek());

        stack.pop();
        stack.pop();
        stack.pop();
        System.out.println(stack);
    }

    private MyLinkedList2<E> linkedList;

    public MyLinkedListStack() {
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
    public void push(E e) {
        linkedList.addFirst(e);
    }

    @Override
    public E pop() {
        return linkedList.removeFirst();
    }

    @Override
    public E peek() {
        return linkedList.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("stack size: %d,  \n", getSize()));
        sb.append("stack top[");
        sb.append(linkedList);
        sb.append("]\n");
        return sb.toString();
    }
}
