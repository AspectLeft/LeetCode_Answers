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
    public int maxPathSum(TreeNode root) {
        if (root == null) return sum;
        sum = root.val;
        maxSinglePath(root);
        return sum;
    }
    
    private int maxSinglePath(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        int l = maxSinglePath(root.left), r = maxSinglePath(root.right);
        int p = root.val + (l > 0 ? l : 0) + (r > 0 ? r : 0);
        if (p > sum) sum = p;
        int t = root.val + Math.max(0, Math.max(l, r));
        return t;
    }

}
