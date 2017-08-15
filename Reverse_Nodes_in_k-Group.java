/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        
        if (k == 1) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p = dummy, q;
        while (p.next != null) {
            q = p;
            for (int i = 0; i < k; ++i) {
                q = q.next;
                if (q == null)
                    return dummy.next;
            }
            ListNode a = p, b = p.next, c;
            for (int i = 0; i < k - 1; ++i) {
                c = b.next;
                b.next = c.next;
                c.next = a.next;
                a.next = c;
            }


            p = b;
        }

        return dummy.next;
    }
}
