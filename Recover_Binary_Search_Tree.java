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
    private TreeNode node1 = null, node2 = null;
    TreeNode prev = null;
    
    private void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        if (prev != null && prev.val > root.val) {
            if (node1 == null) {
                node1 = prev;
                node2 = root;
            }
            else 
                node2 = root;
        }
        prev = root;
        inOrder(root.right);
    }
    
    
    public void recoverTree(TreeNode root) {
        inOrder(root);
        int tmp = node1.val;
        node1.val = node2.val;
        node2.val = tmp;
    }
}
