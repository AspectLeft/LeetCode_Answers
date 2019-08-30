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
    private int l_height(TreeNode root) {
        if (root == null) return 0;
        int h = 0;
        TreeNode node = root;
        while (node != null) {
            node = node.left;
            h++;
        }
        return h;
    }
    
    private int r_height(TreeNode root) {
        if (root == null) return 0;
        int h = 0;
        TreeNode node = root;
        while (node != null) {
            node = node.right;
            h++;
        }
        return h;
    }
    
    public int countNodes(TreeNode root) {
        int lh = l_height(root), rh = r_height(root);
        if (lh == rh) return (1 << lh) - 1;
        int r = ones(lh), l = 0, mid;
        while (l < r) {
            mid = l + (r - l) / 2;
            if (bottom(root, mid, lh) == null) {
                r = mid;
            }
            else {
                l = mid + 1;
            }
        }
        return (1 << rh) - 1 + l;
    }
    
    private int ones(int h) {
        int v = 0;
        for (int i = 0; i < h; ++i) {
            v = (v << 1) + 1;
        }
        return v;
    }
    
    private TreeNode bottom(TreeNode root, int path, int lh) {
        int mask = 1 << (lh - 2);
        TreeNode node = root;
        while (mask > 0) {
            if ((path & mask) != 0) {
                node = node.right;
            }
            else {
                node = node.left;
            }
            mask = mask >> 1;
        }
        return node;
    }
}
