package stuct.segmenttree;

import java.util.Stack;

/**
 * desc  :  链表线段树  未完成 查询和更新
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/3/13 16 12
 */
public class LinkedSegmentTree<E> {

    public static void main(String[] args) {
        Integer[] arr = new Integer[6];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        LinkedSegmentTree<Integer> tree = new LinkedSegmentTree<>(arr, new Merger<Integer>() {
            @Override
            public Integer merge(Integer a, Integer b) {
//                return a > b ? a : b;
//                return a < b ? a : b;
                return a + b; //
            }
        });
        System.out.println(tree);

//        System.out.println(tree.query(1, 6));
    }

    private Merger<E> merger;
    private Node<E> tree;
    private E[] data;

    private class Node<E> {
        int l, r;
        Node<E> left, right;
        E value;

        public Node(int l, int r, Node<E> left, Node<E> right) {
            this.l = l;
            this.r = r;
            this.left = left;
            this.right = right;
        }

        public Node(int l, int r, Node<E> left, Node<E> right, E value) {
            this(l, r, left, right);
            this.value = value;
        }
    }

    public LinkedSegmentTree(E[] arr, Merger<E> merger) {
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
//        this.tree = (E[]) new Object[4 * arr.length];
        this.merger = merger;

        tree = buildSegmentTree(0, arr.length - 1);
    }

    private Node<E> buildSegmentTree(int l, int r) {
        if (l > r) {
            return null;
        } else if (l == r) {
            return new Node<>(l, r, null, null, data[l]);
        } else {
            int mid = l + (r - l) / 2;
            Node<E> leftNode = buildSegmentTree(l, mid);
            Node<E> rightNode = buildSegmentTree(mid + 1, r);
            return new Node<>(l, r, leftNode, rightNode, merger.merge(leftNode.value, rightNode.value));
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("data: [ ");
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {
                builder.append(data[i]);
            } else {
                builder.append("null");
            }
            if (i != data.length - 1) {
                builder.append(", ");
            }
        }
        builder.append(" ]\n");

        Stack<Node> stack = new Stack<>();
        stack.push(tree);
        Node p;
        builder.append("tree: [ ");
        while (!stack.isEmpty()) {
            p = stack.pop();
            if (p != null) {
                builder.append(p.value);
                if (p.left != null) {
                    builder.append("("+p.left.value+")");
                    stack.push(p.left);
                }

                if (p.right != null) {
                    builder.append("("+p.right.value+")");
                    stack.push(p.right);
                }
                builder.append(", ");
            }
        }
        builder.append(" ]\n");
        return builder.toString();
    }
    /*
          10 (3,7)
        7(3,4)     3(1,2)
      4   3       1(0,1)  2
                  0, 1

     */
}
