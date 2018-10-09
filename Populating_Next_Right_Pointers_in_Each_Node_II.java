/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null) return;
        TreeLinkNode p = root, q = null, next_root;
        while (p != null && p.left == null && p.right == null) p = p.next;
        if (p != null) {
            if (p.left != null) {
                next_root = p.left;
                if (p.right != null) {
                    next_root.next = p.right;
                    q = p.right;
                }
                else q = p.left;
            }
            else {
                next_root = p.right;
                q = p.right;
            }
            p = p.next;
            while (p != null) {
                if (p.left != null) {
                    q.next = p.left;
                    q = q.next;
                }
                if (p.right != null) {
                    q.next = p.right;
                    q = q.next;
                }
                p = p.next;
            }
            connect(next_root);
        }
    }
    
}
