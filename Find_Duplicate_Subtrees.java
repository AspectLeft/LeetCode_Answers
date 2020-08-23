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
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        postOrder(root);
        
        return new ArrayList<>(set);
    }
    
    Map<String, TreeNode> map = new HashMap<>();
    Set<TreeNode> set = new HashSet<>();
    
    private String postOrder(TreeNode root) {
        if (root == null) return "";
        String l = postOrder(root.left);
        String r = postOrder(root.right);
        String s = String.format("[%s][%s]%d", l, r, root.val);
        
        if (map.containsKey(s)) {
            set.add(map.get(s));
        }
        else {
            map.put(s, root);
        }
        
        return s;
    }
}
