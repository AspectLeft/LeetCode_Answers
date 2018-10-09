/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    private TreeLinkNode uncle = null;
    public void connect(TreeLinkNode root) {
        if (root == null) return;
        connect(root.left);
        connect(root.right);
        TreeLinkNode l = root.left, r = root.right;
        
        while (l != null) {
            l.next = r;
            l = l.right;
            r = r.left;
        }
        
    }
}
