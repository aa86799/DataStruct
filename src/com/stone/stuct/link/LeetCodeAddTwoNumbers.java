package com.stone.stuct.link;

/**
 * desc  :  https://leetcode-cn.com/problems/add-two-numbers/
 *
 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

 示例：

 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 输出：7 -> 0 -> 8
 原因：342 + 465 = 807

 * author: stone
 * email : aa86799@163.com
 * time  : 2018/12/26 20 05
 */
public class LeetCodeAddTwoNumbers {

    public static void main(String[] args) {
//        ListNode l1 = new ListNode(2);
//        l1.next = new ListNode(4);
//        l1.next.next = new ListNode(3);
//
//        ListNode l2 = new ListNode(5);
//        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(4);

        ListNode l1 = new ListNode(5);
        l1.next = new ListNode(5);
        l1.next.next = new ListNode(8);
        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(5);
        l2.next.next = new ListNode(4);
        l2.next.next.next = new ListNode(4);

        System.out.println(l1);
        System.out.println(l2);
        System.out.println(addTwoNumbers(l1, l2));
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        /*
         * 头结点存储个位，逆序存储
         *
         */

        boolean isUp = false; //同位相加，是否进1
        int cur;
        ListNode listNode = new ListNode(0);
        ListNode p = listNode;
        int v1, v2;
        while (l1 != null || l2 != null) {
            v1 = v2 = 0;
            if (l1 != null)
                v1 = l1.val;
            if (l2 != null)
                v2 = l2.val;

            //同位相加
            if (isUp) {
                cur = v1 + v2 + 1;
            } else {
                cur = v1 + v2;
            }

            //同位和是否进1
            if (cur / 10 == 1) {
                isUp = true;
            } else {
                isUp = false;
            }

            cur = cur % 10;
            p.next = new ListNode(cur);
            p = p.next;

            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }
        //当l1、l2位数相同，且进1时，发会生 isUp = true;
        if (isUp) {
            p.next = new ListNode(1);
        }

        return listNode.next;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(val);
            ListNode p = next;
            while (p != null) {
                sb.append("->" + p.val);
                p = p.next;
            }
            return sb.toString();
        }
    }
}
