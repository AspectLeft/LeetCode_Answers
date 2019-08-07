/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1.val == 0) return l2;
        if (l2.val == 0) return l1;
        int length1 = getLength(l1), length2 = getLength(l2);
        if (length1 < length2) {
            int length = length2;
            length2 = length1;
            length1 = length;
            
            ListNode l = l1;
            l1 = l2;
            l2 = l;
        }
        
        ListNode dup = new ListNode(0), p1 = l1, p2 = l2, q;
        for (int i = 0; i < length1 - length2; ++i) {
            q = new ListNode(p1.val);
            q.next = dup.next;
            dup.next = q;
            p1 = p1.next;
        }
        for (int i = 0; i < length2; ++i) {
            q = new ListNode(p1.val + p2.val);
            q.next = dup.next;
            dup.next = q;
            p1 = p1.next;
            p2 = p2.next;
        }
        q = dup.next;
        int carry = 0;
        for (int i = 0; i < length1; ++i) {
            q.val += carry;
            if (q.val > 9) {
                q.val -= 10;
                carry = 1;
            }
            else {
                carry = 0;
            }
            q = q.next;
        }
        ListNode l3 = new ListNode(0);
        while (dup.next != null) {
            q = dup.next;
            dup.next = q.next;
            q.next = l3.next;
            l3.next = q;
        }
        if (carry > 0) {
            q = new ListNode(1);
            q.next = l3.next;
            l3.next = q;
        }
        return l3.next;
    }
    
    private int getLength(ListNode l) {
        int length = 0;
        while (l != null) {
            length++;
            l = l.next;
        }
        return length;
    }
}
