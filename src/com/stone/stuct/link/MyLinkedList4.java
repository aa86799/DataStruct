package com.stone.stuct.link;


/**
 * desc  :
 * author: stone
 * email : aa86799@163.com
 * time  : 2018/12/24 12 39
 */
public class MyLinkedList4<E> {

    /*
     * 循环链表，前head、tail 相连; 从任一结点开始，都可以遍历完整个链表
     * 单(向)循环链表：tail.next = head;
     * 双(向)循环链表： head.prev = tail;  tail.net = head;
     *
     * 数组实现单链表:
     *  底层数组实现，存储 Node {E e; int next 下个节点的索引}
     *     int head, 记录头结点在数组中的索引；
     *     int findFreeIndex() { 循环遍历，判断 ary[i].e == null }，返回可存储 node 的索引位置
     *     指定位置 add：
     *          int newNodeIndex = findFreeIndex();
     *          Node newNode = new Node(e, -1);
     *          if (addIndex == 0) {
     *                 newNode.next = head;
     *                 head = newNodeIndex;
     *          } else {
     *              int p = head;
     *              int count = 0;
     *              while(count < addIndex - 1) {
     *                  p = ary[p].next;
     *                  count++;
     *              }
     *              newNode.next = ary[p].next;
     *              ary[p].next = newNodeIndex;
     *          }
     *      删除指定位置与 add 类似
     *          一样的循环遍历, 得到 p，这个要删除的前一索引
     *              ary[p].next = ary[ary[p].next].next;
     *          要考虑如果要删除的是最后一个
     *              ary[p].next = -1;
     *          删除第一个
     *              head = ary[ary[head].next];
     */
}
