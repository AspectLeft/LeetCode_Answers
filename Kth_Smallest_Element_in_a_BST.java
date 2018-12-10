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
    public int kthSmallest(TreeNode root, int k) {
        int l = size(root.left);
        if (l >= k) {
            return kthSmallest(root.left, k);
        }
        else if (l == k - 1) return root.val;
        else return kthSmallest(root.right, k - 1 - l);
    }
    
    private int size(TreeNode root) {
        if (root == null) return 0;
        return 1 + size(root.left) + size(root.right);
    }
}
