package com.stone.stuct.link;

/**
 * desc  : https://leetcode-cn.com/problems/remove-linked-list-elements/
 *
 *
 * 删除链表中等于给定值 val 的所有节点。
     示例:
     输入: 1->2->6->3->4->5->6, val = 6
     输出: 1->2->3->4->5

 * author: stone
 * email : aa86799@163.com
 * time  : 2018/12/25 13 14
 */
public class LeetCodeRemoveLinkedListEle {

    public static void main(String[] args) {

        ListNode listNode = arrayToListNode(new int[]{
                10, 20, 30, 40, 50,
                10, 20, 30, 40, 50
        });

        System.out.println(listNode);

//        System.out.println(removeElements(listNode, 40));
//        System.out.println(removeElements(listNode, 20));
//        System.out.println(removeElements(listNode, 50));
//        System.out.println(removeElements(listNode, 10));

//        System.out.println(removeElements2(listNode, 40));
//        System.out.println(removeElements2(listNode, 20));
//        System.out.println(removeElements2(listNode, 50));
//        System.out.println(removeElements2(listNode, 10));

//        System.out.println(removeElements3(listNode, 40));
//        System.out.println(removeElements3(listNode, 20));
//        System.out.println(removeElements3(listNode, 50));
//        System.out.println(removeElements3(listNode, 10));

        System.out.println(removeElements4(listNode, 40));
        System.out.println(removeElements4(listNode, 20));
        System.out.println(removeElements4(listNode, 50));
        System.out.println(removeElements4(listNode, 10));
    }

    private static ListNode arrayToListNode(int[] values) {
        if (values == null || values.length == 0) return null;

        ListNode head = new ListNode(values[0]);
        ListNode p = head;
        for (int i = 1, len = values.length; i < len; i++) {
            p.next = new ListNode(values[i]);
            p = p.next;
        }
        return head;
    }

    public static ListNode removeElements(ListNode head, int val) {
        //头结点相匹配 删除
        while (head != null && head.val == val) {
//            ListNode delNode = head;
            head = head.next;
//            delNode = null;   //方法体执行完后，java 有自己的垃圾回收机制；可以不处理
        }
        if (head == null) {
            return null;
        }

        //非头结点
        ListNode p = head;
        while (p.next != null) {
            if (p.next.val == val) {
//                ListNode delNode = p.next;
//                p.next = delNode.next;
//                delNode = null; //方法体执行完后，java 有自己的垃圾回收机制；可以不处理
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }
        return head;
    }

    //使用虚拟头结点; 不需要对头结点做处理
    public static ListNode removeElements2(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode p = dummyHead;
        while (p.next != null) {
            if (p.next.val == val) {
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }
        return dummyHead.next;
    }

    //使用递归
    public static ListNode removeElements3(ListNode head, int val) {
        if (head == null) return null;
        if (head.val == val) {//处理头结点
            head = head.next;
            head = removeElements3(head, val);
        } else {//处理非头结点
            head.next = removeElements3(head.next, val);
        }

        return head;
    }

    //使用递归
    public static ListNode removeElements4(ListNode head, int val) {
        if (head == null) return null;
//        ListNode result = removeElements4(head.next, val);
//        if (head.val == val) {
//            return result;
//        } else {
//            head.next = result;
//            return head;
//        }

        head.next = removeElements4(head.next, val);
        if (head.val == val) {
            head = head.next;
        }
        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(val + "->");
            ListNode p = next;
            while (p != null) {
                sb.append(p.val + "->");
                p = p.next;
            }
            return sb.toString();
        }
    }
}
