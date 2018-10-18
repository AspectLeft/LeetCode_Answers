/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    
    
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = slow.next;
        slow.next = null;
        ListNode first = sortList(head), second = sortList(fast);
        ListNode dup = new ListNode(0), p = dup;
        while (first != null && second != null) {
            if (first.val < second.val) {
                p.next = first;
                p = p.next;
                first = first.next;
                p.next = null;
            }
            else {
                p.next = second;
                p = p.next;
                second = second.next;
                p.next = null;
            }
        }
        if (first != null) {
            p.next = first;
        }
        if (second != null) {
            p.next = second;
        }
        return dup.next;
    }
}
