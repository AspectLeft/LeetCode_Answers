/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dup = new ListNode(0);
        dup.next = head;
        int count = 1;
        ListNode p = head;
        ListNode newHead = new ListNode(0), tail = newHead;
        while (p.next != null) {
            if (p.next.val != p.val) {
                if (count <= 1) {
                    tail.next = p;
                    tail = tail.next;
                }
                count = 1;
            }
            else {
                count++;
            }
            p = p.next;
        }
        
        if (count <= 1) {
            tail.next = p;
            tail = tail.next;
        }
        tail.next = null;
        return newHead.next;
    }
}
