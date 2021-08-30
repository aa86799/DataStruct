package stuct.bst.detail;

import java.util.*;

public class BST01<E extends Comparable<E>> {

    private class Node {
        E e;
        Node left;
        Node right;
        int depth; //所在的深度

        public Node(E e) {
            this.e = e;
        }

        public Node(E e, int depth) {
            this(e);
            this.depth = depth;
        }
    }

    public static void main(String[] args) {
        BST01<Integer> bst = new BST01<>();
//        Random random = new Random();
//        for (int i = 0; i < 10; i++) {
//            bst.add(random.nextInt(100));
//        }
        bst.add(33);
        bst.add(31);
        bst.add(36);
        bst.add(19);
        bst.add(92);
        bst.add(9);
        bst.add(38);
        bst.add(98);
        bst.add(43);
        bst.add(54);
        bst.add(54);
        bst.add(98);
        bst.add(19);

        /*
         33
        31 36
        19   92
        9    38 98
               43
                54
         */

        bst.levelOrder2();
        System.out.println(bst.getDepth(bst.root, 0));
    }

    private Node root;

    public void add(E e) {
        root = add(root, e, 0);
    }

    private Node add(Node node, E e, int depth) {
        if (node == null) {
            return new Node(e, depth);
        }
        if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e, depth + 1);
        } else /*if (e.compareTo(node.e) <= 0)*/ {
            node.left = add(node.left, e, depth + 1);
        }
        return node;
    }

    public void addByCircle(E e) {
        if (root == null) {
            root = new Node(e);
            root.depth = 0;
        } else {
            Node p = root;
            while (p != null) {
                if (e.compareTo(p.e) > 0) {
                    if (p.right == null) {
                        p.right = new Node(e, p.depth + 1);
                        break;
                    } else {
                        p = p.right;
                    }
                } else /*if (e.compareTo(p.e) < 0)*/ {
                    if (p.left == null) {
                        p.left = new Node(e, p.depth + 1);
                        break;
                    } else {
                        p = p.left;
                    }
                } /*else {
                    break;
                }*/
            }
        }
    }

    public void levelOrder1() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            sb.append(node.e + " ");
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        System.out.println(sb);
    }

    public void levelOrder2() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        StringBuilder sb = new StringBuilder();
        int tempDepth = -1;
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            if (tempDepth != node.depth) {
                sb.append("\n");
            }
            sb.append(node.e + "(depth=" + node.depth +") ");
            tempDepth = node.depth;
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        System.out.println(sb);
    }

    //求总的深度
    public int getDepth(Node node, int i) {
        if (node == null) {
            return i;
        }
        int left = getDepth(node.left, i + 1);
        int right = getDepth(node.right, i + 1);
        return left >= right ? left : right;
    }
}
