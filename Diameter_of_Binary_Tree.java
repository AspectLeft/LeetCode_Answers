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
    public int diameterOfBinaryTree(TreeNode root) {
        return search(root)[0];
    }
    
    // [d, h]
    private int[] search(TreeNode root) {
        if (root == null) return new int[]{0, 0};
        int[] l = search(root.left);
        int[] r = search(root.right);
        return new int[]{Math.max(l[0], Math.max(r[0], l[1] + r[1])), 
                         1 + Math.max(l[1], r[1])};
    }
}
