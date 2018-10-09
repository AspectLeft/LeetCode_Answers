/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public void flatten(TreeNode root) {
        f(root);
    }
    
    private TreeNode f(TreeNode root) {
        if (root == null) return null;
        TreeNode lt = f(root.left);
        TreeNode rt = f(root.right);
        if (lt == null) return rt == null ? root : rt;
        lt.right = root.right;
        root.right = root.left;
        root.left = null;
        return rt == null ? lt : rt;
    }
}
