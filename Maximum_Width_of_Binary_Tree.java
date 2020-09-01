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
    List<Long> minList = new ArrayList<>();
    List<Long> maxList = new ArrayList<>();
    
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        
        traverse(root, 0, 0);
        
        int maxWidth = 0;
        for (int i = 0; i < minList.size(); ++i) {
            maxWidth = Math.max(maxWidth, (int) (maxList.get(i) - minList.get(i)));
        }
        
        return maxWidth + 1;
    }
    
    private void traverse(TreeNode root, long code, int d) {
        if (root == null) return;
        
        if (d >= minList.size()) {
            minList.add(code);
        }
        else {
            minList.set(d, Math.min(code, minList.get(d)));
        }
        
        if (d >= maxList.size()) {
            maxList.add(code);
        }
        else {
            maxList.set(d, Math.max(code, maxList.get(d)));
        }
        
        traverse(root.left, code << 1, d + 1);
        traverse(root.right, (code << 1) + 1, d + 1);
    }
}
