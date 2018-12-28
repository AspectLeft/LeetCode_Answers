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
    private int[] dp(TreeNode root) {
        if (root == null) return new int[3];
        int[] l = dp(root.left), r = dp(root.right);
        int[] result = new int[3];
        result[1] = l[0];
        result[2] = r[0];
        result[0] = Math.max(root.val + l[1] + l[2] + r[1] + r[2], l[0] + r[0]);
        return result;
    }
    
    
    
    public int rob(TreeNode root) {
        return dp(root)[0];
    }
}
