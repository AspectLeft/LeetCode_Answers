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
        TreeNode root;
        int i;
        
        Dfs(TreeNode r, int i) {
            this.root = r;
            this.i = i;
        }
    }
    
    
    public TreeNode str2tree(String s) {
        if (s == null || s.length() == 0) return null;
        return dfs(s, 0).root;
    }
    
    private boolean isDigit(char c) {
        return '0' <= c && c <= '9' || c == '-';
    }
    
    private Dfs dfs(String s, int i) {
        if (i == s.length() || !isDigit(s.charAt(i))) {
            return new Dfs(null, i);
        }
        int val = 0;
        int sign = 1;
        while (i < s.length() && isDigit(s.charAt(i))) {
            if (s.charAt(i) == '-') {
                sign = -1;
                i++;
                continue;
            }
            val *= 10;
            val += s.charAt(i) - '0';
            i++;
        }
        val *= sign;
        TreeNode root = new TreeNode(val);
        if (i >= s.length()) return new Dfs(root, i);
        if (s.charAt(i) == ')') {
            return new Dfs(root, i + 1);
        }
        // (
        Dfs ldfs = dfs(s, i + 1);
        root.left = ldfs.root;
        i = ldfs.i;
        if (i >= s.length()) return new Dfs(root, i);
        if (s.charAt(i) == ')') {
            return new Dfs(root, i + 1);
        }
        // (
        Dfs rdfs = dfs(s, i + 1);
        root.right = rdfs.root;
        i = rdfs.i;
        if (i >= s.length()) return new Dfs(root, i);
        if (s.charAt(i) == ')') return new Dfs(root, i + 1);
        return new Dfs(root, i);
    }
}
