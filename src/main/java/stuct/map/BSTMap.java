package stuct.map;


import stuct.FileOperation;

import java.util.ArrayList;

/**
 * desc  :
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/3/10 20 08
 */
public class BSTMap<K extends Comparable<K>, V> implements IMap<K, V> {

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("src/pride-and-prejudice.txt", words)) {
            System.out.println("total size: " + words.size());
            BSTMap<String, Integer> map = new BSTMap<>();
            for (String word : words) {
                if (map.contains(word)) {
                    map.set(word, map.get(word) + 1);
                } else {
                    map.add(word, 1);
                }
            }

            System.out.println("total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
        }

        System.out.println("total time:" + ((System.nanoTime() - startTime) / 1.0e9));
    }

    private Node root;
    private int size;

    private class Node {
        K key;
        V value;
        Node left;
        Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }


    @Override
    public V put(K key, V value) {
//        root = addNode(root, key, value);
        return null;
    }

    @Override
    public void add(K key, V value) {
        root = addNode(root, key, value);
    }

    private Node addNode(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) > 0) {
            node.right = addNode(node.right, key, value);
        } else if (key.compareTo(node.key) < 0) {
            node.left = addNode(node.left, key, value);
        } else {//key == node.key
            node.value = value;
        }

        return node;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) == 0) {
            return node;
        } else if (key.compareTo(node.key) > 0) {
            return getNode(node.right, key);
        } else {
            return getNode(node.left, key);
        }
    }

    @Override
    public V remove(K key) {
        Node node = remove(root, key);
        return node == null ? null : node.value;
    }

    private Node remove(Node node, K key) {
        if (node == null) return null;

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            } else {
                /*
                 * 左右子节点都不为空
                 * 找到右子树中的最小节点，置为被删除节点的位置
                 */
                Node retNode = minimum(node.right);
                retNode.right = removeMin(node.right);//删除右子树最小值，返回该右子中的根节点
                retNode.left = node.left;
                node.left = node.right = null;
                return retNode;
            }
        }
    }

    public V minimum() {
        return minimum(root).value;
    }

    private Node minimum(Node node) {
        if (node == null) return null;
        if (node.left == null) return node;
        return minimum(node.left);
    }

    public V maximum() {
        return maximum(root).value;
    }

    private Node maximum(Node node) {
        if (node == null) return null;
        if (node.right == null) return node;
        return maximum(node.right);
    }

    public V removeMin() {
        V e = minimum();
        root = removeMin(root);
        return e;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    public V removeMax() {
        V e = maximum();
        root = removeMax(root);
        return e;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(root, key);
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
        // 见 bst 中的 遍历
        return super.toString();
    }
}
