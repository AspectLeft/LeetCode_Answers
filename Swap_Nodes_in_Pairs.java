/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode p = new ListNode(0), q;
        p.next = head;
        ListNode dummy = p;
        while (p .next != null && p.next.next != null) {
            q = p.next;
            p.next = q.next;
            q.next = p.next.next;
            p.next.next = q;
            p = p.next.next;
        }
        return dummy.next;
    }
}
