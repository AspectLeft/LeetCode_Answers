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
    
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return mergeKListsRange(lists, 0, lists.length - 1);
    }
    
    public ListNode mergeKListsRange(ListNode[] lists, int l, int r) {
        if (l == r) return lists[l];
        if (l + 1 == r) return mergeTwoLists(lists[l], lists[r]);
        int mid = (l + r) / 2;
        return mergeTwoLists(mergeKListsRange(lists, l, mid), mergeKListsRange(lists, mid + 1, r));
    }
}
