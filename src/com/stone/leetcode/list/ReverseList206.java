package com.stone.leetcode.list;

/**
 * desc:    https://leetcode-cn.com/problems/reverse-linked-list/  [206. 链表反转]
 *          1->2->3->4->5->null, 反转成 5->4->3->2->1->null
 * author:  stone
 * email:   aa86799@163.com
 * time:    2020/9/6 02:27
 */

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }
}

public class ReverseList206 {

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode originNext = cur.next;
            cur.next = prev;
            prev = cur;
            cur = originNext;
        }
        return prev;
    }

    //1,2,3
    //return 3
    //  3.next = 2; 2.next=null retunr 3;
    //  cur=3; 2.next = 1; 1.next = null; return 3;
    public ListNode reverseListWithRecursion(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = reverseListWithRecursion(head.next);
        head.next.next = head;
        head.next = null;
        return cur;
    }
}
