package stuct.redblack;


import stuct.FileOperation;
import stuct.avl.AVLTreeMap;
import stuct.map.BSTMap;

import java.util.ArrayList;

/**
 * desc  : 红黑树
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/3/10 20 08
 */
@SuppressWarnings("all")
public class RBTree<K extends Comparable<K>, V> {

    public static void main(String[] args) {

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("src/pride-and-prejudice.txt", words)) {
            System.out.println("total size: " + words.size());

            //test avl
            long startTime = System.nanoTime();
            AVLTreeMap<String, Integer> map = new AVLTreeMap<>();
            for (String word : words) {
                if (map.contains(word)) {
                    map.set(word, map.get(word) + 1);
                } else {
                    map.add(word, 1);
                }
            }
            System.out.println("avl total time:" + ((System.nanoTime() - startTime) / 1.0e9));


            //test bstMap
            startTime = System.nanoTime();
            BSTMap<String, Integer> bstMap = new BSTMap<>();
            for (String word : words) {
                if (bstMap.contains(word)) {
                    bstMap.set(word, bstMap.get(word) + 1);
                } else {
                    bstMap.add(word, 1);
                }
            }
            System.out.println("bstMap total time:" + ((System.nanoTime() - startTime) / 1.0e9));

            //test rbTree
            startTime = System.nanoTime();
            RBTree<String, Integer> rbTree = new RBTree<>();
            for (String word : words) {
                if (rbTree.contains(word)) {
                    rbTree.set(word, rbTree.get(word) + 1);
                } else {
                    rbTree.add(word, 1);
                }
            }
            System.out.println("rbTree total time:" + ((System.nanoTime() - startTime) / 1.0e9));

            System.out.println("total different words: " + rbTree.getSize());
            System.out.println("Frequency of PRIDE: " + rbTree.get("pride"));
        }

    }

    private Node root;
    private int size;

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        K key;
        V value;
        Node left;
        Node right;
        boolean color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.color = RED;
        }
    }

    private boolean isRed(Node node) {
        if (node == null) return BLACK;
        return node.color;
    }

    /*
     * 对节点 y 左旋转
     *      y                    x
     *     / \                 /   \
     *    t4  x               y     z
     *        /\     ---->   / \    /\
     *       t3 z           t4 t3  t1 t2
     *          /\
     *         t1 t2
     */
    private Node leftRotate(Node y) {
//        Node x = y.right;
//        Node t3 = x.left;
//
//        //左转
//        x.left = y;
//        y.right = t3;

        Node x = y.right;
        //左转
        y.right = x.left;
        x.left = y;

        x.color = y.color;
        y.color = RED;
        return x;
    }

    /*
     *  对节点 y 右旋转
     *      y                         x
     *     /\                       /   \
     *    x  t4                    z     y
     *    /\        --->          /\    / \
     *   z  t3                   t1 t2  t3 t4
     *   /\
     *  t1 t2
     */
    private Node rightRotate(Node y) {
//        Node x = y.left;
//        Node t3 = x.right;
//
//        //右转
//        x.right = y;
//        y.left = t3;

        Node x = y.left;

        //右转
        y.left = x.right;
        x.right = y;

        x.color = y.color;
        y.color = RED;

        return x;
    }

    //颜色翻转
    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    public V put(K key, V value) {
//        root = addNode(root, key, value);
        return null;
    }

    public void add(K key, V value) {
        root = addNode(root, key, value);
        root.color = BLACK; // 最终根节点为黑色节点
    }

    private Node addNode(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value); //默认插入红色节点
        }

        if (key.compareTo(node.key) > 0) {
            node.right = addNode(node.right, key, value);
        } else if (key.compareTo(node.key) < 0) {
            node.left = addNode(node.left, key, value);
        } else {//key == node.key
            node.value = value;
        }

        //左黑右红
        if (isRed(node.right) && !isRed(node.left)) {
            node = leftRotate(node);
        }

        //左红 左左红
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rightRotate(node);
        }

        //左红右红
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }

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

    public V remove(K key) {
        Node node = remove(root, key);
        return node == null ? null : node.value;
    }

    private Node remove(Node node, K key) {
        if (node == null) return null;

        //未实现
        return null;
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

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node != null) {
            node.value = newValue;
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public String toString() {
        // 见 bst 中的 遍历
        return super.toString();
    }
}
