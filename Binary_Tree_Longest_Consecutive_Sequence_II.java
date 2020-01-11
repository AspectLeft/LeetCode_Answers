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
    class Dfs {
        int asc, dsc;
        
        Dfs(int a, int d) {
            asc = a;
            dsc = d;
        }
    }
    
    private int result = 0;
    
    
    public int longestConsecutive(TreeNode root) {
        if (root == null) return result;
        dfs(root);
        return result;
    }
    
    private Dfs dfs(TreeNode root) {
        if (root == null) return new Dfs(0, 0);
        Dfs ldfs = dfs(root.left);
        Dfs rdfs = dfs(root.right);
        
        int asc = 1, dsc = 1;
        if (root.left != null) {
            if (root.left.val - 1 == root.val) {
                asc = Math.max(asc, 1 + ldfs.asc);
            }
            if (root.left.val + 1 == root.val) {
                dsc = Math.max(dsc, 1 + ldfs.dsc);
            }
        }
        if (root.right != null) {
            if (root.right.val - 1 == root.val) {
                asc = Math.max(asc, 1 + rdfs.asc);
            }
            if (root.right.val + 1 == root.val) {
                dsc = Math.max(dsc, 1 + rdfs.dsc);
            }
        }
        
        result = Math.max(result, asc + dsc - 1);
        
        return new Dfs(asc, dsc);
    }
}
