package stuct.map;


import stuct.FileOperation;

import java.util.ArrayList;

/**
 * desc  : 基于链表的映射
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/3/10 18 35
 */
public class LinkedListMap<K, V> implements IMap<K, V> {

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("src/pride-and-prejudice.txt", words)) {
            System.out.println("total size: " + words.size());
            LinkedListMap<String, Integer> map = new LinkedListMap<>();
            for (String word : words) {
                if (map.contains(word)) {
                    map.set(word, map.get(word) + 1);
                } else {
                    map.put(word, 1);
                }
            }

            System.out.println("total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
        }

        System.out.println("total time:" + ((System.nanoTime() - startTime) / 1.0e9));
    }

    private Node dummyHead;
    private int size;

    public LinkedListMap() {
        dummyHead = new Node();
        size = 0;
    }

    private class Node {
        K key;
        V value;
        Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key) {
            this(key, null, null);
        }

        public Node() {
            this(null, null, null);
        }

        @Override
        public String toString() {
            return key.toString() + ": " + value.toString();
        }
    }

    private Node getNode(K key) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.key.equals(key)) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    @Override //key已经存在，返回 对应的value(value 也可能为 null)， 反之直接返回 null
    public V put(K key, V value) {
        Node node = getNode(key);

        if (node != null) {
            V preValue = node.value;
            node.value = value;
            return preValue; //may null
        } else {
            dummyHead.next = new Node(key, value, dummyHead.next);
            size++;
            return null;
        }
    }

    @Override
    public void add(K key, V value) {
        Node node = getNode(key);

        if (node != null) {
            node.value = value;
        } else {
            dummyHead.next = new Node(key, value, dummyHead.next);
            size++;
        }
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    @Override
    public V remove(K key) {
        Node  cur = dummyHead;
        while (cur.next != null) {
            if (cur.next.key.equals(key)) {
                break;
            }
            cur = cur.next;
        }
        if (cur.next != null) {
            Node delNode = cur.next;
            cur.next = delNode.next;
            delNode.next = null;
            return delNode.value;
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(key);
        if (node != null) {
            node.value = newValue;
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
    public String toString() {
        //dummyHead 遍历
        return super.toString();
    }
}
