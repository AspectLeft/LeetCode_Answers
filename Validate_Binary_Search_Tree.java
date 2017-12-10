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
    private class BSTProperty {
        boolean isValid;
        int min;
        int max;
        BSTProperty(boolean b, int min, int max) {
            isValid = b;
            this.min = min;
            this.max = max;
        }
    }

    private BSTProperty isValidBSTRecursive(TreeNode root) {
        
        if (root.left == null && root.right == null) {
            return new BSTProperty(true, root.val, root.val);
        }
        else if (root.left == null) {
            BSTProperty right = isValidBSTRecursive(root.right);
            return new BSTProperty(right.isValid && right.min > root.val, root.val, right.max);
        }
        else if (root.right == null) {
            BSTProperty left = isValidBSTRecursive(root.left);
            return new BSTProperty(left.isValid && left.max < root.val, left.min, root.val);
        }
        else {
            BSTProperty left = isValidBSTRecursive(root.left), right = isValidBSTRecursive(root.right);
            return new BSTProperty(left.isValid && right.isValid && left.max < root.val && right.min > root.val,
                    left.min, right.max);
        }
        
    }

    public boolean isValidBST(TreeNode root) {
        return root == null || isValidBSTRecursive(root).isValid;
    }
}
