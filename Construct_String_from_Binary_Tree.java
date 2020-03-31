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
    private StringBuilder builder;
    
    public String tree2str(TreeNode t) {
        if (t == null) return "";
        builder = new StringBuilder();
        preorder(t);
        return builder.toString();
    }
    
    private void preorder(TreeNode t) {
        if (t == null) return;
        builder.append(t.val);
        if (t.left == null && t.right == null) return;
        builder.append('(');
        preorder(t.left);
        builder.append(')');
        if (t.right == null) return;
        builder.append('(');
        preorder(t.right);
        builder.append(')');
    }
}
