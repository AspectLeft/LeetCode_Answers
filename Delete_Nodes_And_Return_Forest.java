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
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> set = new HashSet<>();
        for (int v: to_delete) set.add(v);
        forest = new LinkedList<>();
        helper(root, set, true);
        return forest;
    }
    
    private List<TreeNode> forest;
    
    private void helper(TreeNode root, Set<Integer> to_delete, boolean isRoot) {
        if (root == null) return;
        TreeNode left = root.left, right = root.right;
        
        if (left != null && to_delete.contains(left.val)) {
            root.left = null;
        }
        if (right != null && to_delete.contains(right.val)) {
            root.right = null;
        }
        if (to_delete.contains(root.val)) {
            to_delete.remove(root.val);
            root = null;
        }
        else {
            if (isRoot)
                forest.add(root);   
        }
        
        helper(left, to_delete, root == null);
        helper(right, to_delete, root == null);
        return;
    }
}
