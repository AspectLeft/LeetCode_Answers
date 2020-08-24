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
    private int getH(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(getH(root.left), getH(root.right));
    }
    
    private int getN(int m) {
        if (m == 1) return 1;
        return 2 * getN(m - 1) + 1;
    }
    
    public List<List<String>> printTree(TreeNode root) {
        int m = getH(root);
        int n = getN(m);
        
        List<List<String>> container = new ArrayList<>(m);
        for (int i = 0; i < m; ++i) {
            container.add(new ArrayList<>(n));
        }
        
        fill(container, root, 0, m, 0, n);
        
        return container;
    }
    
    private void fill(List<List<String>> container, TreeNode root, int i1, int i2, int j1, int j2) {
        if (root == null) {
            for (int i = i1; i < i2; ++i) {
                for (int j = j1; j < j2; ++j) {
                    container.get(i).add("");
                }
            }
            return;
        }
        int j3 = (j1 + j2) / 2;
        List<String> rootLine = container.get(i1);
        for (int j = j1; j < j3; ++j) {
            rootLine.add("");
        }
        rootLine.add(String.valueOf(root.val));
        for (int j = j3 + 1; j < j2; ++j) {
            rootLine.add("");
        }
        
        fill(container, root.left, i1 + 1, i2, j1, j3);
        for (int i = i1 + 1; i < i2; ++i) {
            container.get(i).add("");
        }
        fill(container, root.right, i1 + 1, i2, j3 + 1, j2);
    }
    
}
