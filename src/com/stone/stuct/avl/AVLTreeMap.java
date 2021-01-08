package com.stone.stuct.avl;


import com.stone.stuct.FileOperation;
import com.stone.stuct.map.BSTMap;

import java.util.ArrayList;

/**
 * desc  : 从 BSTMap 演变，符合 AVL 特性
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/3/23 15 38
 */
@SuppressWarnings({"Duplicates", "SuspiciousNameCombination", "UnusedReturnValue", "WeakerAccess", "unused"})
public class AVLTreeMap<K extends Comparable<K>, V> {

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

            System.out.println("total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("isBST: " + map.isBST());
            System.out.println("isBalance: " + map.isBalance());

//            map.removeMax();
//            map.removeMin();
            map.remove("prejudice");
            map.remove("pride");
            System.out.println("删除后 isBST: " + map.isBST());
            System.out.println("删除后 isBalance: " + map.isBalance());
        }


    }

    private Node root;
    private int size;

    private class Node {
        K key;
        V value;
        Node left;
        Node right;
        int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }
    }

    private int getHeight(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    private int getBalanceFactor(Node node) {
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
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
        Node x = y.right;
        Node t3 = x.left;

        //左转
        x.left = y;
        y.right = t3;

        //更新 height
        y.height = Math.max(getHeight(t3), getHeight(y.left)) + 1;
        x.height = Math.max(getHeight(x.right), getHeight(y)) + 1;
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
        Node x = y.left;
        Node t3 = x.right;

        //右转
        x.right = y;
        y.left = t3;

        //更新 height
        y.height = Math.max(getHeight(t3), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(y)) + 1;
        return x;
    }

    public V put(K key, V value) {
//        root = addNode(root, key, value);
        return null;
    }

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

        //更新 height
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        int balanceFactor = getBalanceFactor(node);
//        if (Math.abs(balanceFactor) > 1) {
//            System.out.println("不平衡");
//        }

        /*
         * 当平衡因子绝对值大于1也就是2时， 要进行处理，使整体平衡
         */
        //LL  不平衡发生在 左子的左子
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }
        //RR 右子的右子
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }
        //LR  左子的右子
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        //RL  右子的左子
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
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

        Node retNode;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            } else {
                /*
                 * 左右子节点都不为空
                 * 找到右子树中的最小节点，置为被删除节点的位置
                 */
                Node n = minimum(node.right);
//                n.right = removeMin(node.right);//删除右子树最小值，返回该右子中的根节点 //没有维护平衡性
                n.right = remove(node.right, n.key);//
                n.left = node.left;
                node.left = node.right = null;

                retNode = n;
            }
        }

        if (retNode != null) {
            retNode.height = Math.max(getHeight(retNode.left), getHeight(retNode.right)) + 1;
            int balanceFactor = getBalanceFactor(retNode);
            //维护平衡性
            //LL  不平衡发生在 左子的左子
            if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
                return rightRotate(retNode);
            }
            //RR 右子的右子
            if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
                return leftRotate(retNode);
            }
            //LR  左子的右子
            if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
                retNode.left = leftRotate(retNode.left);
                return rightRotate(retNode);
            }
            //RL  右子的左子
            if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
                retNode.right = rightRotate(retNode.right);
                return leftRotate(retNode);
            }
        }

        return retNode;
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

    //没有维护平衡性
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

    //没有维护平衡性
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

    //是否有二分搜索树的性质
    public boolean isBST() {
        ArrayList<K> list = new ArrayList<>();
        inOrder(root, list);
        for (int i = 1, len = list.size(); i < len; i++) {
            if (list.get(i).compareTo(list.get(i - 1)) < 0) {
                return false;
            }
        }
        return true;
    }

    //中序遍历 从小到大
    private void inOrder(Node node, ArrayList<K> keys) {
        if (node == null)
            return;
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    //是否平衡
    public boolean isBalance() {
        return isBalance(root);
    }

    private boolean isBalance(Node node) {
        if (node == null)
            return true;
        int balanceFactor = getBalanceFactor(node);
        return Math.abs(balanceFactor) <= 1 && isBalance(node.right) && isBalance(node.left);
    }

    public String toString() {
        // 见 bst 中的 遍历
        return super.toString();
    }
}
