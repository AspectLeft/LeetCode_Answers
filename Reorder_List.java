/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return;
        ListNode p = head, q = head;
        while (q != null && q.next != null) {
            p = p.next;
            q = q.next.next;
        }
        ListNode mid = p.next;
        p.next = null;
        p = mid; 
        q = mid.next;
        ListNode prev = null;
        while (q != null) {
            p.next = prev;
            prev = p;
            p = q;
            q = q.next;
        }
        p.next = prev;
        q = p;
        ListNode r = head, s = head.next;
        while (q != null) {
            r.next = q;
            prev = q;
            q = q.next;
            prev.next = s;
            r = s;
            s = r.next;
        }
        
    }
}
