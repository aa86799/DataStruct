package com.stone.stuct.array;

/**
 * desc  : 自定义的数组 静态容量
 * author: stone
 * email : aa86799@163.com
 * time  : 2018/12/22 16 09
 */
public class MyArray {

    public static void main(String[] args) {
        MyArray ary = new MyArray(20);
//        ary.add(3, 8); //error
        for (int i = 0, len = ary.getCapacity() / 2; i < len; i++) {
            ary.addLast(i*10);
        }
        ary.set(3, 88);
        ary.add(3, 99);
        System.out.println(ary);

        ary.remove(2);
        System.out.println(ary);

        ary.removeElement(99);
        System.out.println(ary);

        ary.removeFirst();
        ary.removeLast();
        System.out.println(ary);
    }

    /*
     * 系统提供的数组，是一个静态的数组；要让其有 增删的功能，需要一个自定义的数组结构
     */

    private int size; //实际大小
    private int[] data;
    private static int defaultCapacity = 10;

    public MyArray(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    public MyArray() {
        this(defaultCapacity);
    }

    public int getSize() {//实际大小
        return size;
    }

    public int getCapacity() {//总容量
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(int e) {
        add(0, e);
    }

    public void addLast(int e) {
//        if (size == getCapacity()) {
//            throw new IllegalArgumentException("addLast failed. array is full");
//        }
//
//        data[size] = e;
//        size++;

        add(size, e);
    }

    public void add(int index, int e) {
        if (size == getCapacity()) {
            throw new IllegalArgumentException("add failed. array is full");
        }

        if (index < 0 || index > size) {
            throw new IllegalArgumentException("add failed. require index >=0 and <=size");
        }

        for (int i = size - 1; i >= index; i--) {//向后移
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    public int remove(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("remove failed. require index >=0 and <=size");
        }
        int ret = data[index];
        for (int i = index; i < size; i++) {//向前移
            data[i] = data[i + 1];
        }
        size--;
        return ret;
    }

    public int removeFirst() {
        return remove(0);
    }

    public int removeLast() {
        return remove(size - 1);
    }

    public boolean removeElement(int e) {
        int index = find(e);
        boolean flag = false;
        if (index != -1) {
            remove(index);
            flag = true;
        }
        return flag;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("get failed. index is illegal");
        }
        return data[index];
    }

    public void set(int index, int e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("set failed. index is illegal");
        }
        data[index] = e;
    }

    public boolean isContains(int e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return true;
            }
        }
        return false;
    }

    public int find(int e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("array size: %d, capacity: %d, array: \n", size, getCapacity()));
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]\n");
        return sb.toString();
    }
}
