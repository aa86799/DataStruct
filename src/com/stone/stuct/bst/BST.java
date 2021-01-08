package com.stone.stuct.bst;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * desc  : 二分(或叫二叉)搜索树，
 *          前序、中序、后序遍历，都是 深度优先遍历
 *          层序遍历，即一层一层的遍历，是 广度优先遍历
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/1/3 10 10
 */
public class BST<E extends Comparable<E>> {

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
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
        System.out.println(bst);
        /*
         *                       60
         *               40             70
         *                  45        64   75
         *                42  58        68
         */
        System.out.println(bst.contains(60));
        System.out.println(bst.contains(40));
        System.out.println(bst.contains(70));
        System.out.println(bst.contains(45));
        System.out.println(bst.contains(64));
        System.out.println(bst.contains(75));
        System.out.println(bst.contains(42));
        System.out.println(bst.contains(58));
        System.out.println(bst.contains(68));
        System.out.println(bst.contains(69));

        System.out.println("minimum: " + bst.minimum());
        System.out.println("maximum: " + bst.maximum());

        System.out.println("remove min: " + bst.removeMin());
        System.out.println("remove max: " + bst.removeMax());
        System.out.println(bst);
        bst.remove(45);
        System.out.println(bst);

        System.out.println("floor: " + bst.getFloor(66));
        System.out.println("ceil: " + bst.getCeil(66));
    }

    private Node root;
    private int size;

    public BST() {

    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
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
        root = addNode(root, e);
    }

    private Node addNode(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) > 0) {
            node.right = addNode(node.right, e);
        } else if(e.compareTo(node.e) < 0) {
            node.left = addNode(node.left, e);
        }

        return node;
    }

    private class Node {
        E e;
        Node left;
        Node right;

        public Node(E e) {
            this.e = e;
        }

//        public Node(E e, Node left, Node right) {
//            this.e = e;
//            this.left = left;
//            this.right = right;
//        }
    }

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

    //循环遍历
    public String preOrderCircle() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        StringBuilder sb = new StringBuilder();
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
            sb.append(generateDepthString(depth) + "null\n");
        } else {
            sb.append(generateDepthString(depth) + node.e + "\n");
            preOrder(node.left, depth + 1, sb);
            preOrder(node.right, depth + 1, sb);
            sb.append("\n");
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
        return inOrder(root, new StringBuilder());
    }

    /**
     * 递归遍历. 中序遍历: 先访问左，再访问该结点，再访问右
     *          中序遍历，相当于进行了顺序排序遍历，从小到大
     *          如果访问顺序为：右 中 左 ，则从到大小排序
     * @return
     */
    private String inOrder(Node node, StringBuilder sb) {
        if (node == null) return "";
        inOrder(node.left, sb);
        sb.append(node.e + "\n");
        inOrder(node.right, sb);
        return sb.toString();
    }

    private String postOrder() {
        return postOrder(root, new StringBuilder());
    }

    /**
     * 递归遍历. 后序遍历: 先访问左, 再访问右，再访问该结点
     *      后序遍历的一个应用：左、右、中遍历完成，最后可以释放中的资源
     * @param node
     * @param sb
     * @return
     */
    private String postOrder(Node node, StringBuilder sb) {
        if (node == null) return "";
        postOrder(node.left, sb);
        postOrder(node.right, sb);
        sb.append(node.e + "\n");
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
            sb.append(node.e + " ");
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

    public E removeMin() {
        E e = minimum();
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

    public E removeMax() {
        E e = maximum();
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

    public void remove(E e) {
        root = remove(root, e);
    }

    private Node remove(Node node, E e) {
        if (node == null) return null;

        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
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
        if (e.compareTo(node.e) == 0) {
            return node;
        } else if (e.compareTo(node.e) < 0) {
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
        if (e.compareTo(node.e) == 0) {
            return node;
        } else if (e.compareTo(node.e) > 0) {
            return getCeil(node.right, e);
        } else {
            Node tempNode = getCeil(node.left, e);
            if (tempNode != null) return tempNode;
            return node;
        }
    }

    @Override
    public String toString() {
//        return preOrder() + "size=" + getSize();
//        return preOrderCircle() + "size=" + getSize();
//        return inOrder() + "size=" + getSize();
//        return postOrder() + "size=" + getSize();
        return levelOrder() + "size=" + getSize();
    }

//    public E[] toArray() {
//        E[] ary = (E[]) new Object[size];
//        int i = 0;
//        Queue<Node> queue = new LinkedList<>();
//        queue.add(root);
//        while (!queue.isEmpty()) {
//            Node node = queue.remove();
//            ary[i++] = node.e;
//            if (node.left != null)
//                queue.add(node.left);
//            if (node.right != null)
//                queue.add(node.right);
//        }
//        return ary;
//    }
}
