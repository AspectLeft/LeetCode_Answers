/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode tail, p = headA, q = headB;
        while (p.next != null) p = p.next;
        while (q.next != null) q = q.next;
        if (p != q) return null;
        tail = p;
        tail.next = headA;
        ListNode c = detectCycle(headB);
        tail.next = null;
        return c;
    }
    
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode p = head, q = head;
        while (q != null && q.next != null) {
            p = p.next;
            q = q.next.next;
            if (p == q) {
                ListNode r = head;
                while (r != q) {
                    r = r.next;
                    q = q.next;
                }
                return r;
            }
        }
        return null;
    }
}
