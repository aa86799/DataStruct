package com.stone.stuct.trie;

import java.util.TreeMap;

/**
 * desc  : 字典树，递归实现。查找完整词，前缀词；添加
 *
 *  直接套用 解决：
 *  leetcode 208  https://coding.imooc.com/lesson/207.html#mid=13854
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/3/13 18 30
 */
public class Trie1 {

    public static void main(String[] args) {
        Trie1 trie = new Trie1();

        trie.insert("apple");
        boolean f1 = trie.search("apple");   // 返回 true
        boolean f2 = trie.search("app");     // 返回 false
        boolean f3 = trie.startsWith("app"); // 返回 true
        trie.insert("app");
        boolean f4 = trie.search("app");     // 返回 true

        System.out.println(f1 + "," + f2 + "," + f3 + "," + f4);
        System.out.println(trie.root.next.get('a').next.get('p').next.get('p').isWorld);

    }
    private Node root;
    public Trie1() {
        root = new Node();
    }
    private class Node {
        TreeMap<Character, Node> next;
        boolean isWorld;

        public Node() {
            this(false);
        }
        public Node(boolean isWorld) {
            this.next = new TreeMap<>();
            this.isWorld = isWorld;
        }
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null || word.length() == 0) return;
        insert(root, word, 0);
    }

    private void insert(Node node, String word, int index) {
        if (index == word.length()) {
            node.isWorld = true;
            return;
        }

        char c = word.charAt(index);
        if (node.next.get(c) == null) {
            node.next.put(c, new Node());
        }
        insert(node.next.get(c), word, index + 1);
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if (word == null || word.length() == 0) return false;
        return search(root, word, 0);
    }

    private boolean search(Node node, String word, int index) {
        char c = word.charAt(index);
        Node next = node.next.get(c);
        if (next == null) {
            return false;
        }
        if (index == word.length() - 1) {
            return next.isWorld;
        }
        return search(next, word, index + 1);

    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.length() == 0) return false;
        return startsWith(root, prefix, 0);
    }

    private boolean startsWith(Node node, String prefix, int index) {

        char c = prefix.charAt(index);
        Node next = node.next.get(c);
        if (next == null) {
            return false;
        }
        if (index == prefix.length() - 1) {
            return true;
        }
        return startsWith(next, prefix, index + 1);
    }
}
