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
    int modeCount = 1;
    int length = 0;
    int[] output;
    int i = 0;
    
    public int[] findMode(TreeNode root) {
        if (root == null) return new int[]{};
        updateModeCount(root);
        updateLength(root);
        output = new int[length];
        updateOutput(root);
        return output;
    }
    
    private void updateOutput(TreeNode root) {
        if (root == null) return;
        if (count(root, root.val) == modeCount) {
            output[i] = root.val;
            i++;
        }
        updateOutput(root.left);
        updateOutput(root.right);
    }
    
    private void updateLength(TreeNode root) {
        if (root == null) return;
        if (count(root, root.val) == modeCount) length++;
        updateLength(root.left);
        updateLength(root.right);
    }
    
    private void updateModeCount(TreeNode root) {
        if (root == null) return;
        modeCount = Math.max(modeCount, count(root, root.val));
        updateModeCount(root.left);
        updateModeCount(root.right);
    }
    
    private int count(TreeNode root, int val) {
        if (root == null) return 0;
        if (root.val == val) {
            return 1 + count(root.left, val) + count(root.right, val);
        }
        else if (root.val < val) {
            return count(root.right, val);
        }
        else {
            return count(root.left, val);
        }
    }
}
