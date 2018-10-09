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
        if (root.left != null)
            link(root.left, root.right);
    }
    
    private void link(TreeLinkNode l, TreeLinkNode r) {
        l.next = r;
        if (l.left != null) {
            link(l.left, l.right);
            link(l.right, r.left);
            link(r.left, r.right);
        }
    }
}
