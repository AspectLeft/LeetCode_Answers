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
    private int count = 0;
    
    public int countUnivalSubtrees(TreeNode root) {
        postOrder(root);
        return count;
    }
    
    private boolean postOrder(TreeNode root) {
        if (root == null) return true;
        boolean l = postOrder(root.left);
        boolean r = postOrder(root.right);
        if (l && r) {
            if ((root.left == null || root.left.val == root.val)
               && (root.right == null || root.right.val == root.val)) {
                count++;
                return true;
            }
        }
        return false;
    }
}
