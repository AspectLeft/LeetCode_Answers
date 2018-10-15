/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return null;
        RandomListNode p = head, q;
        while (p != null) {
            q = new RandomListNode(p.label);
            q.next = p.next;
            p.next = q;
            p = q.next;
        }
        p = head;
        while (p != null) {
            q = p.next;
            q.random = p.random;
            if (q.random != null)
                q.random = q.random.next;
            p = q.next;
        }
        p = head;
        RandomListNode dup = new RandomListNode(0), r = dup;
        
        while (p != null) {
            q = p.next;
            p.next = q.next;
            r.next = q;
            r = q;
            p = p.next;
        }
        return dup.next;
    }
}
