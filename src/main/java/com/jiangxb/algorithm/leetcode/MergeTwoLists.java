package com.jiangxb.algorithm.leetcode;

/**
 * 将两个有序链表 合并成一个有序链表
 */
public class MergeTwoLists {
    public static void main(String[] args) {

        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(4);

        ListNode node2 = new ListNode(1);
        node2.next = new ListNode(3);
        node2.next.next = new ListNode(4);

        ListNode listNode = mergeTwoLists(node1, node2);

    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode head = null;
        if (l1.val <= l2.val) {
            head = l1;
            head.next = mergeTwoLists(l1.next, l2);
        } else {
            head = l2;
            head.next = mergeTwoLists(l1, l2.next);
        }
        return head;
    }

}



class ListNode{

    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}