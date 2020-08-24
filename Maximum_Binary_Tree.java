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
    
    
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        Stack<TreeNode> rightStack = new Stack<>();
        TreeNode prev, node;
        TreeNode root = null;
        for (int v: nums) {
            prev = null;
            while (!rightStack.isEmpty() && v > rightStack.peek().val) {
                prev = rightStack.pop();
            }
            node = new TreeNode(v);
            if (!rightStack.isEmpty()) {
                rightStack.peek().right = node;
            }
            node.left = prev;
            rightStack.push(node);
            if (root == null || root.val < node.val) root = node;
        }
        return root;
    }
}
