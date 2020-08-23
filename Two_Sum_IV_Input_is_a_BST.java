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
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;
        Stack<TreeNode> lstack = new Stack<>();
        TreeNode node = root;
        while (node != null) {
            lstack.push(node);
            node = node.left;
        }
        
        Stack<TreeNode> rstack = new Stack<>();
        node = root;
        while (node != null) {
            rstack.push(node);
            node = node.right;
        }
        
        TreeNode l = lstack.pop(), r = rstack.pop();
        
        int sum;
        while (l.val <= r.val) {
            if (l == r) return false;
            sum = l.val + r.val;
            if (sum == k) return true;
            if (sum < k) {
                l = l.right;
                while (l != null) {
                    lstack.push(l);
                    l = l.left;
                }
                l = lstack.pop();
            }
            else {
                r = r.left;
                while (r != null) {
                    rstack.push(r);
                    r = r.right;
                }
                r = rstack.pop();
            }
        }
        
        return false;
    }
}
