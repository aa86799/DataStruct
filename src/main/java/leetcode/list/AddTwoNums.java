package leetcode.list;

/**
 * desc:   2. 两数相加
 *          https://leetcode-cn.com/problems/add-two-numbers/
 * author:  stone
 * email:   aa86799@163.com
 * blog :   https://stone.blog.csdn.net
 * time:    2022/2/12 16:20
 */
public class AddTwoNums {

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String[] args) {
        // 从最小位加到最大位，逆序存储，首位就是最小位
        ListNode l1 = new ListNode(2);
        l1.next =  new ListNode(4);
        l1.next.next = new ListNode(8);

        ListNode l2 = new ListNode(5);
        l2.next =  new ListNode(6);
        l2.next.next = new ListNode(4);
        l2.next.next.next = new ListNode(9);

        addTwoNumbers(l1, l2);
    }

    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        boolean isUp = false; // 是否要升一级，上级+1
        int cur;
        ListNode list = new ListNode();
        ListNode p = list;
        while (l1 != null || l2 != null) {
            int v1 = 0, v2 = 0;
            if (l1 != null) v1 = l1.val;
            if (l2 != null) v2 = l2.val;
            if (isUp) {
                cur = v1 + v2 + 1;
            } else {
                cur = v1 + v2;
            }
            if (cur >= 10) {
                cur = cur % 10;
                isUp = true;
            } else {
                isUp = false;
            }
            p.next = new ListNode(cur);
            p = p.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (isUp) {
            p.next = new ListNode(1);
        }
        return list.next;
    }

    private static ListNode reverse(ListNode src) {//243
        ListNode reverse = null;
        while (src != null) {
            ListNode temp = reverse;
            reverse = new ListNode(src.val);
            reverse.next = temp;
            src = src.next;
        }
        return reverse;
    }
}
