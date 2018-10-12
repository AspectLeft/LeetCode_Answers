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
    private int sum = 0;
    
    
    public int sumNumbers(TreeNode root) {
        if (root == null) return sum;
        dfs(root, 0);
        return sum;
    }
    
    private void dfs(TreeNode root, int head) {
        if (root.left == null && root.right == null) {
            sum += head * 10 + root.val;
            return;
        }
        if (root.left != null)
            dfs(root.left, head * 10 + root.val);
        if (root.right != null)
            dfs(root.right, head * 10 + root.val);
    }
}
