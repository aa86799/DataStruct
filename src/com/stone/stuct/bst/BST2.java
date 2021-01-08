package com.stone.stuct.bst;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

/**
 * desc  : 二分(或叫二叉)搜索树，
 *          前序、中序、后序遍历，都是 深度优先遍历
 *          层序遍历，即一层一层的遍历，是 广度优先遍历
 *
 *          添加 rank、select 方法
 *              为了实现这两个，在 Node 中添加 一个 size 属性
 *          add 方法中，每个新增的元素 node.size++;
 *          remove 相关方法中，需要重新遍历到 删除方法返回的子树根节点之上，都要重置 size--;
 *
 *          对深度优先遍历中的 前、中、后序遍历，都添加了深度属性操作，即 node.depth 相关操作
 *
 *          暂未实现：
 *          如果要有存储重复元素，那么可以 left <= e  right > e
 *               同时，可以为节点添加一个 count 属性，表示同值元素有几个
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/1/3 10 10
 */
public class BST2<E extends Comparable<E>> {

    public static void main(String[] args) {
        BST2<Integer> bst = new BST2<>();
//        bst.add(30);
        bst.add(60);
        bst.add(40);
        bst.add(70);
        bst.add(45);
        bst.add(75);
        bst.add(42);
        bst.add(64);
        bst.add(58);
        bst.add(68);
//        Random random = new Random();
//        for (int i = 0; i < 20000; i++) {
//            bst.add(random.nextInt(100000));
//        }
        System.out.println(bst);
        System.out.println("----");
        /*
         *                       60
         *               40             70
         *                  45        64   75
         *                42  58        68
         */
        System.out.println(bst.contains(60));
//        System.out.println(bst.contains(40));
//        System.out.println(bst.contains(70));
//        System.out.println(bst.contains(45));
//        System.out.println(bst.contains(64));
//        System.out.println(bst.contains(75));
//        System.out.println(bst.contains(42));
//        System.out.println(bst.contains(58));
//        System.out.println(bst.contains(68));
        System.out.println(bst.contains(69));

        System.out.println("--前序--");
        System.out.println(bst.preOrder()); //前序遍历
        System.out.println("--中序--");
        System.out.println(bst.inOrder()); //中序遍历
        System.out.println("--后序--");
        System.out.println(bst.postOrder());//后序遍历

        System.out.println("minimum: " + bst.minimum());
        System.out.println("maximum: " + bst.maximum());
        System.out.println("----");

        System.out.println("remove min: " + bst.removeMin());
        System.out.println("remove max: " + bst.removeMax());
        System.out.println("after remove size: " + bst.root.size);
        System.out.println(bst);
        bst.remove(45);
        System.out.println(bst);
        System.out.println("root size: " + bst.root.size);
        System.out.println("root.left size: " + bst.root.left.size);
        System.out.println("root.right size: " + bst.root.right.size);
        System.out.println("----");

        System.out.println("floor: " + bst.getFloor(66));
        System.out.println("ceil: " + bst.getCeil(66));
        System.out.println("----");

        System.out.println("select rank 0: " + bst.select(0));
        System.out.println("select rank 1: " + bst.select(1));
        System.out.println("select rank 2: " + bst.select(2));
        System.out.println("select rank 3: " + bst.select(3));
        System.out.println("select rank 4: " + bst.select(4));
        System.out.println("select rank 5: " + bst.select(5));
        System.out.println("rank 60: " + bst.rank(60));
        System.out.println("rank 58: " + bst.rank(58));
        System.out.println("rank 70: " + bst.rank(70));
        System.out.println("rank 42: " + bst.rank(42));
        System.out.println("rank 64: " + bst.rank(64));
        System.out.println("rank 68: " + bst.rank(68));
    }

    private Node root;

    public BST2() {

    }

    public int getSize() {
        return getSize(root);
    }

    private int getSize(Node node) {
        if (node == null) {
            return 0;
        }
        return node.size;
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    //循环实现
    /*public void add(E e) {
        if (root == null) {
            root = new Node(e);
        } else {
            Node temp = root;
            while (temp != null) {
                if (e.compareTo(temp.e) == 0) {
                    return;
                } else if (e.compareTo(temp.e) > 0) {
                    if (temp.right == null) {
                        temp.right = new Node(e);
                        size++;
                        return;
                    } else {
                        temp = temp.right;
                    }
                } else { //e.compareTo(temp.e) < 0
                    if (temp.left == null) {
                        temp.left = new Node(e);
                        size++;
                        return;
                    } else {
                        temp = temp.left;
                    }
                }
            }
        }
    }*/

    //处理非 root，调用递归实现
    /*public void add(E e) {
        if (root == null) {
            root = new Node(e);
        } else {
            addNode(root, e);
        }
    }

    private void addNode(Node node, E e) {
        if (e.equals(node.e)) return;
        if (e.compareTo(node.e) > 0) {
            if (node.right == null) {
                node.right = new Node(e);
                size++;
            } else {
                addNode(node.right, e);
            }
        } else {
            if (node.left == null) {
                node.left = new Node(e);
                size++;
            } else {
                addNode(node.left, e);
            }
        }
    }*/

    //递归实现，不单独考虑 root == null 的 case; 逻辑&代码更简洁
    public void add(E e) {
        if (!contains(e)) {
            root = addNode(root, e);
        }
    }

    private Node addNode(Node node, E e) {
        if (node == null) {
            return new Node(e);
        }

        if (e.compareTo(node.e) > 0) {
            node.right = addNode(node.right, e);
            node.size++;
        } else if (e.compareTo(node.e) < 0) {
            node.left = addNode(node.left, e);
            node.size++;
        }

        return node;
    }

    private class Node {
        E e;
        Node left;
        Node right;
        int size;
        int depth;

        public Node(E e) {
            this.e = e;
            size++;
        }
//
//        public Node(E e, Node left, Node right) {
//            this.e = e;
//            this.left = left;
//            this.right = right;
//            if (this.left != null) size++;
//            if (this.right != null) size++;
//        }
    }

    //时间复杂度是 O(h)  h 就是树的层次高度
    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        if (node == null) return false;
        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) > 0) {
            return contains(node.right, e);
        } else {
            return contains(node.left, e);
        }
    }

    //循环遍历: 输出的结果顺序， 先root、再 左子树 ，最后 右子树
    //与 preOrder 的递归遍历结果相同
    public String preOrderCircle() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        StringBuilder sb = new StringBuilder();
        int depth = 0;
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            sb.append(node.e + " ");
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);
        }
        sb.append("\n");
        return sb.toString();
    }

    public String preOrder() {
        return preOrder(root, 0, new StringBuilder());
    }

    /**
     * 递归遍历. 前序遍历：先访问该结点，再访问左，再访问右
     * @param node
     * @param depth 层次深度
     * @param sb
     * @return
     */
    private String preOrder(Node node, int depth, StringBuilder sb) {
        if (node == null) {
//            sb.append(generateDepthString(depth) + "null\n");
        } else {
            node.depth = depth;
            sb.append(generateDepthString(depth) + node.e + "(depth=" + node.depth +")"+ "(size=" + node.size +")\n");
            preOrder(node.left, depth + 1, sb);
            preOrder(node.right, depth + 1, sb);
        }
        return sb.toString();
    }

    private String generateDepthString(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("-");
        }
        return sb.toString();
    }

    public String inOrder() {
        return inOrder(root, 0, new StringBuilder());
    }

    /**
     * 递归遍历. 中序遍历: 先访问左，再访问该结点，再访问右
     *          中序遍历，相当于进行了顺序排序遍历，从小到大
     *          如果访问顺序为：右 中 左 ，则从到大小排序
     * @return
     */
    private String inOrder(Node node, int depth, StringBuilder sb) {
        if (node == null) return "";
        /*从小到大*/
//        inOrder(node.left, depth + 1, sb);
//        node.depth = depth;
//        sb.append(node.e + "(" + node.depth +")\n");
//        inOrder(node.right, depth + 1,sb);

        /*从大到小*/
        inOrder(node.right, depth + 1,sb);
        node.depth = depth;
        sb.append(node.e + "(" + node.depth +")\n");
        inOrder(node.left, depth + 1, sb);

        return sb.toString();
    }

    private String postOrder() {
        return postOrder(root, 0, new StringBuilder());
    }

    /**
     * 递归遍历. 后序遍历: 先访问左, 再访问右，再访问该结点
     *      后序遍历的一个应用：左、右、中遍历完成，最后可以释放中的资源
     * @param node
     * @param sb
     * @return
     */
    private String postOrder(Node node, int depth, StringBuilder sb) {
        if (node == null) return "";
        postOrder(node.left, depth + 1, sb);
        postOrder(node.right, depth + 1, sb);
        node.depth = depth;
        sb.append(node.e + "(" + node.depth +")\n");
        node = null; //释放资源
        return sb.toString();
    }

    //层序(广度优先)遍历
    public String levelOrder() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            sb.append(node.e + "(size=" + node.size + ") ");
            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);
        }
        sb.append("\n");
        return sb.toString();
    }

    public E minimum() {
        return minimum(root).e;
    }

    private Node minimum(Node node) {
        if (node == null) return null;
        if (node.left == null) return node;
        return minimum(node.left);
    }

    public E maximum() {
        return maximum(root).e;
    }

    private Node maximum(Node node) {
        if (node == null) return null;
        if (node.right == null) return node;
        return maximum(node.right);
    }

    private void setNodeSize(Node node, E e) {
        int cmp = e.compareTo(node.e);
        if (cmp == 0) {
            node.size--;
        } else if (cmp < 0) {
            node.size--;
            setNodeSize(node.left, e);
        } else {
            node.size--;
            setNodeSize(node.right, e);
        }
    }

    public E removeMin() {
        E e = minimum();
        root = removeMin(root);
        return e;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            setNodeSize(root, node.e);
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    public E removeMax() {
        E e = maximum();
        root = removeMax(root);
        return e;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            setNodeSize(root, node.e);
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    public void remove(E e) {
        root = remove(root, e);
    }

    private Node remove(Node node, E e) {
        if (node == null) return null;

        int cmp = e.compareTo(node.e);
        if (cmp < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (cmp > 0) {
            node.right = remove(node.right, e);
            return node;
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                setNodeSize(root, node.e);
                return rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                setNodeSize(root, node.e);
                return leftNode;
            } else {
                /*
                 * 左右子节点都不为空
                 * 找到右子树中的最小节点，置为被删除节点的位置
                 */
                Node retNode = minimum(node.right);
                retNode.right = removeMin(node.right);//删除右子树最小值，返回该右子树中的根节点
                retNode.left = node.left;
                /*
                 * 上面 retNode.right = removeMin(node.right) 这里 node.right 树中已经减过1了
                 */
                retNode.size = node.size;
                node.left = node.right = null;
                return retNode;
            }
        }
    }

    //地板元素： 返回比你传入key 最接近并且小于等于的元素
    public E getFloor(E e) {
        if (e.compareTo(minimum()) < 0) {
            return null;
        }
        return getFloor(root, e).e;
    }

    private Node getFloor(Node node, E e) {
        if (node == null) {
            return null;
        }
        int cmp = e.compareTo(node.e);
        if (cmp == 0) {
            return node;
        } else if (cmp < 0) {
            return getFloor(node.left, e);
        } else {// if (e.compareTo(node.e) > 0) {
            Node tempNode = getFloor(node.right, e);
            if (tempNode != null) return tempNode;
            return node;
        }
    }

    //天花板元素： 返回比你传入key 最接近并且大于等于的元素
    public E getCeil(E e) {
        if (e.compareTo(maximum()) > 0) {
            return null;
        }
        return getCeil(root, e).e;
    }

    private Node getCeil(Node node, E e) {
        if (node == null) {
            return null;
        }
        int cmp = e.compareTo(node.e);
        if (cmp == 0) {
            return node;
        } else if (cmp > 0) {
            return getCeil(node.right, e);
        } else {
            Node tempNode = getCeil(node.left, e);
            if (tempNode != null) return tempNode;
            return node;
        }
    }

    //指定元素在树中的排名
    public int rank(E e) {
        return rank(root, e);
    }

    private int rank(Node node, E e) {
        if (node == null) return 0;

        int cmp = e.compareTo(node.e);
        if (cmp < 0) {
            return rank(node.left, e);
        } else if (cmp > 0) {
            return 1 + getSize(node.left) + rank(node.right, e);
        } else {
            return getSize(node.left);
        }
    }

    //指定排名的元素
    public E select(int rank) {
        return select(root, rank).e;
    }

    private Node select(Node node, int rank) {
        if (node == null) return null;
        int leftSize = getSize(node.left);
        if (leftSize < rank) {
            return select(node.right, rank - leftSize - 1);
        } else if (leftSize > rank) {
            return select(node.left, rank);
        } else {
            return node;
        }
    }

    @Override
    public String toString() {
//        return preOrder() + "total size=" + getSize();
//        return preOrderCircle() + "total size=" + getSize();
//        return inOrder() + "total size=" + getSize();
//        return postOrder() + "total size=" + getSize();
        return levelOrder() + "total size=" + getSize();
    }
}
