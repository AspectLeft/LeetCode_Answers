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
    private int result = 0;
    
    public int longestConsecutive(TreeNode root) {
        longest(root);
        return result;
    }
    
    private int longest(TreeNode root) {
        if (root == null) return 0;
        int l = longest(root.left);
        int r = longest(root.right);
        int v = 1;
        if (l > 0 && root.val + 1 == root.left.val) {
            v += l;
        }
        if (r > 0 && root.val + 1 == root.right.val) {
            v = Math.max(v, r + 1);
        }
        if (v > result) result = v;
        return v;
    }
}
