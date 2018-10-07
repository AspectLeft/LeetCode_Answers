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
    private int h;
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            h = 0;
            return true;
        }
        if (isBalanced(root.left)) {
            int lh = h;
            if (isBalanced(root.right)) {
                if (lh - h <= 1 && lh - h >= -1) {
                    h = 1 + (lh > h ? lh : h);
                    return true;
                }
            }
        }
        return false;
    }
}
