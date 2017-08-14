/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        
        ListNode p = new ListNode(0), q = new ListNode(0), r = new ListNode(0), s = r;
        p.next = l1;
        q.next = l2;
        while (p.next != null && q.next != null) {
            if (p.next.val <= q.next.val) {

                r.next = p.next;
                p.next = p.next.next;
                r = r.next;
            } else {
                r.next = q.next;
                q.next = q.next.next;
                r = r.next;
            }
        }

        if (p.next != null) {
            r.next = p.next;
        }
        if (q.next != null) {
            r.next = q.next;
        }

        return s.next;
    }
}
