/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null) {
            return null;
        }
        ListNode p = new ListNode(0), q = head;
        p.next = head;
        for (int i = 0; i < n; ++i) {
            q = q.next;
        }
        while (q != null) {
            p = p.next;
            q = q.next;
        }
        if (p.next == head) {
            return head.next;
        }
        p.next = p.next.next;
        return head;
    }
}
