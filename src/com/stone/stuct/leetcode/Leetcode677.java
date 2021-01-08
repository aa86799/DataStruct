package com.stone.stuct.leetcode;

import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * desc  : insert(String key, int val)  保存键值对
 *          int sum(String prefix)  模糊匹配键，返回所有匹配的键对应值的 和
 *
 *  https://leetcode-cn.com/problems/map-sum-pairs/submissions/
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/3/13 19 57
 */
public class Leetcode677 {

    public static void main(String[] args) {
        MapSum mapSum = new MapSum();
//        mapSum.insert("apple", 3);
//        mapSum.insert("ap", 8);
//        System.out.println(mapSum.sum("ap"));
//        mapSum.insert("app", 2);
//        System.out.println(mapSum.sum("ap"));

        mapSum.insert("aa", 3);
        System.out.println(mapSum.sum("a"));
        mapSum.insert("aa", 2);
        System.out.println(mapSum.sum("a"));
        System.out.println(mapSum.sum("aa"));
        mapSum.insert("aaa",3);
        System.out.println(mapSum.sum("aaa"));
    }

    /*private static class MapSum {
        private HashMap<String, Integer> map;
        public MapSum() {
            map = new HashMap<>();
        }

        public void insert(String key, int val) {
            map.put(key, val);
        }

        public int sum(String prefix) {
            int sum = 0;
            Set<String> set = map.keySet();
            for (String key : set) {
                if (key.startsWith(prefix)) {
                    sum += map.get(key);
                }
            }
            return sum;
        }
    }*/

    private static class MapSum {
        private Node root;

        public MapSum() {
            root = new Node();
        }

        private class Node {
            TreeMap<Character, Node> next;
            int value;

            Node( int value) {
                next = new TreeMap<>();
                this.value = value;
            }

            Node() {
                this(0);
            }
        }

        public void insert(String word, int val) {
            Node cur = root;
            char c;
            for (int i = 0; i < word.length(); i++) {
                c = word.charAt(i);
                if (cur.next.get(c) == null) {
                    cur.next.put(c, new Node());
                }
                cur = cur.next.get(c);
            }
            cur.value = val;

        }

        //内部不使用递归
        /*public int sum(String prefix) {
            int sum = 0;

            Node cur = root;
            char c;
            for (int i = 0, len = prefix.length(); i < len; i++) {
                c = prefix.charAt(i);
                cur = cur.next.get(c);
                if (cur == null) {//没有相应字母节点
                    return 0;
                }
            }

            sum += cur.value; //这时 prefix 已经完全匹配了

            LinkedList<TreeMap<Character, Node>> linkedList = new LinkedList<>();
            linkedList.add(cur.next);
            while (!linkedList.isEmpty()) {
                TreeMap<Character, Node> map = linkedList.remove();
                for (Map.Entry<Character, Node> entry : map.entrySet()) {
                    sum += entry.getValue().value;
                    linkedList.add(entry.getValue().next);
                }
            }

            return sum;
        }*/

        //内部使用递归
        public int sum(String prefix) {
            Node cur = root;
            char c;
            for (int i = 0, len = prefix.length(); i < len; i++) {
                c = prefix.charAt(i);
                cur = cur.next.get(c);
                if (cur == null) {//没有相应字母节点
                    return 0;
                }
            }
            return sum(cur);
        }

        private int sum(Node node) {
            int sum = node.value;
            TreeMap<Character, Node> map = node.next;
            for (Map.Entry<Character, Node> entry : map.entrySet()) {
                sum += sum(entry.getValue());
            }
            return sum;
        }
    }
}
