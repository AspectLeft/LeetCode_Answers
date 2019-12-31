/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    private ListNode reverse(ListNode head) {
        ListNode dup = new ListNode(0);
        ListNode p = head, q;
        while (p != null) {
            q = p;
            p = p.next;
            q.next = dup.next;
            dup.next = q;
        }
        return dup.next;
    }
    
    public ListNode plusOne(ListNode head) {
        head = reverse(head);
        ListNode p = head;
        head.val++;
        while (p.val == 10) {
            p.val = 0;
            if (p.next == null) {
                p.next = new ListNode(1);
                break;
            }
            p = p.next;
            p.val++;
        }
        return reverse(head);
    }
}
