/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {}

    public Node(int _val,Node _prev,Node _next,Node _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }
};
*/
class Solution {
    class HeadTail {
        Node head, tail;
        HeadTail(Node h, Node t) {
            head = h;
            tail = t;
        }
    }
    
    private void link(Node p, Node q) {
        p.next = q;
        if (q != null)
            q.prev = p;
    }
    
    private HeadTail flat(Node head) {
        if (head == null) return null;
        Node next = head.next;
        Node tail = head;
        if (head.child != null) {
            HeadTail children = flat(head.child);
            link(head, children.head);
            head.child = null;
            tail = children.tail;
        }
        else tail = head;
        if (next != null) {
            HeadTail ht = flat(next);
            link(tail, ht.head);
            return new HeadTail(head, ht.tail);
        }
        else {
            return new HeadTail(head, tail);
        }
    }
    
    public Node flatten(Node head) {
        if (head == null) return null;
        return flat(head).head;
    }
}
