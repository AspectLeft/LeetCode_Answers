/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int half = 0;
    private boolean flag = false;
    
    public boolean checkEqualTree(TreeNode root) {
        if (root == null) return false;
        int s = sum(root);
        if (Math.abs(s % 2) == 1) return false;
        this.half = s / 2;
        this.flag = false;
        check(root);
        return this.flag;
    }
    
    private int sum(TreeNode root) {
        if (root == null) return 0;
        return root.val + sum(root.left) + sum(root.right);
    }
    
    private int check(TreeNode root) {
        if (root == null) return 0;
        int l = check(root.left), r = check(root.right);
        if ((root.left != null && l == half) 
            || (root.right != null && r == half)) {
            flag = true;
            return 0;
        }
        return l + r + root.val;
    }
}
