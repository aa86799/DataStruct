package com.stone.stuct.trie;

import java.util.TreeMap;

/**
 * desc  : 字典树，循环实现。查找完整词，前缀词；添加
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/3/13 17 51
 */
public class Trie {

    private Node root;
    private int size;

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("panda");
        trie.add("pandanus");
        trie.add("pan");

        System.out.println(trie.contains("pan"));
        System.out.println(trie.contains("pand"));
        System.out.println(trie.isPrefix("pand"));
        System.out.println(trie.contains("panda"));
        System.out.println(trie.contains("pandanus"));

    }

    public Trie() {
        root = new Node();
    }

    private class Node {
        boolean isWorld;
        TreeMap<Character, Node> next;

        Node(boolean isWorld) {
            this.isWorld = isWorld;
            next = new TreeMap<>();
        }

        Node() {
            this(false);
        }
    }

    public int getSize() {
        return size;
    }

    public void add(String word) {
        Node cur = root;
        char c;
        for (int i = 0; i < word.length(); i++) {
            c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        if (!cur.isWorld) {
            cur.isWorld = true;
            size++;
        }
    }

    public boolean contains(String word) {
        Node cur = root;
        char c;
        for (int i = 0, len = word.length(); i < len; i++) {
            c = word.charAt(i);
            cur = cur.next.get(c);
            if (cur == null) {//没有相应字母节点
                return false;
            }
        }
        return cur.isWorld; //该 node 是否表示一个单词结尾
    }

    //查询是否有词以 prefix 为前缀
    public boolean isPrefix(String prefix) {
        Node cur = root;
        char c;
        for (int i = 0, len = prefix.length(); i < len; i++) {
            c = prefix.charAt(i);
            cur = cur.next.get(c);
            if (cur == null) {//没有相应字母节点
                return false;
            }
        }
        return true;
    }
}
