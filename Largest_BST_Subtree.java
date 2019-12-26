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
    class Attrs {
        boolean isBST;
        int size;
        int min, max;
        
        Attrs(boolean b, int s, int l, int r) {
            isBST = b;
            size = s;
            min = l;
            max = r;
        }
        
        Attrs(boolean b) {
            isBST = b;
        }
    }
    
    private int maxSize = 0;
    
    private Attrs search(TreeNode root) {
        if (root.left == null && root.right == null) {
            if (maxSize == 0) maxSize = 1;
            return new Attrs(true, 1, root.val, root.val);
        }
        boolean b = true;
        int size = 1;
        int min = root.val, max = root.val;
        if (root.left != null) {
            Attrs lattrs = search(root.left);
            if (!lattrs.isBST) b = false;
            if (!(root.val > lattrs.max)) b = false;
            min = lattrs.min;
            size += lattrs.size;
        }
        if (root.right != null) {
            Attrs rattrs = search(root.right);
            if (!rattrs.isBST) b = false;
            if (!(root.val < rattrs.min)) b = false;
            max = rattrs.max;
            size += rattrs.size;
        }
        if (!b) return new Attrs(false);
        if (size > maxSize) {
            maxSize = size;
        }
        return new Attrs(true, size, min, max);
    }
    
    public int largestBSTSubtree(TreeNode root) {
        if (root == null) return 0;
        search(root);
        return maxSize;
    }
}
