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
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        Stack<TreeNode> stack = new Stack<>();
        if (p.right != null) {
            p = p.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }
        TreeNode current = root;
        while (p != current) {
            if (p.val > current.val) {
                current = current.right;
            }
            else {
                stack.push(current);
                current = current.left;
            }
        }
        if (stack.empty()) return null;
        else return stack.pop();
    }
}
