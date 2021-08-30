package stuct.leetcode;

import java.util.TreeMap;

/**
 * desc  :  设计一个支持以下两种操作的数据结构：
 *              void addWord(word)
 *              bool search(word)
 *              search(word) 可以搜索文字或正则表达式字符串，字符串只包含字母 . 或 a-z 。 . 可以表示任何一个字母。
 *           使用字典树解决
 *
 *  https://leetcode-cn.com/problems/add-and-search-word-data-structure-design/
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/3/13 19 24
 */
public class Leetcode211 {

    public static void main(String[] args) {
        WordDictionary trie = new WordDictionary();
        trie.addWord("bad");
        trie.addWord("dad");
        trie.addWord("mad");
        System.out.println(trie.search("pad"));
        System.out.println(trie.search("bad"));
        System.out.println(trie.search("mad"));
        System.out.println(trie.search(".ad"));
        System.out.println(trie.search("b.."));
        System.out.println(trie.search("..."));
    }

    private static class WordDictionary {
        private Node root;

        public WordDictionary() {
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

        /**
         * Adds a word into the data structure.
         */
        public void addWord(String word) {
            if (word == null || word.length() == 0) return;
            addWord(root, word, 0);
        }

        private void addWord(Node node, String word, int index) {
            if (index == word.length()) {
                node.isWorld = true;
                return;
            }

            char c = word.charAt(index);
            if (node.next.get(c) == null) {
                node.next.put(c, new Node());
            }
            addWord(node.next.get(c), word, index + 1);
        }

        /**
         * Returns if the word is in the data structure.
         * A word could contain the dot character '.' to represent any one letter.
         */
        public boolean search(String word) {
            if (word == null || word.length() == 0) return false;
            return search(root, word, 0);
        }

        private boolean search(Node node, String word, int index) {
            if (index == word.length()) {
                return node.isWorld;
            }

            char c = word.charAt(index);

            if (c != '.') {
                Node next = node.next.get(c);
                if (next == null) {
                    return false;
                }
                return search(next, word, index + 1);
            } else {
                for (char nextChar : node.next.keySet()) {
                    if (search(node.next.get(nextChar), word, index + 1)) {
                        return true;
                    }
                }
                return false;
            }
        }
    }
}
