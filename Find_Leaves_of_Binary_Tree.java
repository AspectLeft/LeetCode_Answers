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
    List<List<Integer>> result;
    
    private int getH(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(getH(root.left), getH(root.right));
    }
    
    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int h = 1 + Math.max(dfs(root.left), dfs(root.right));
        result.get(h - 1).add(root.val);
        return h;
    }
    
    public List<List<Integer>> findLeaves(TreeNode root) {
        if (root == null) return new ArrayList<>();
        int h = getH(root);
        result = new ArrayList<>();
        for (int i = 0; i < h; ++i) {
            result.add(new ArrayList<>());
        }
        dfs(root);
        return result;
    }
}
