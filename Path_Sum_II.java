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
    private List<List<Integer>> result = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();
    private int count = 0;
    private int sum;
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) return result;
        this.sum = sum;
        traverse(root);
        return result;
    }
    
    private void traverse(TreeNode root) {
        if (root == null) return;
        count += root.val;
        path.add(root.val);
        if (root.left == null && root.right == null) {
            if (count == sum) {
                result.add(new ArrayList<>(path));
            }
        }
        traverse(root.left);
        traverse(root.right);
        count -= root.val;
        path.remove(path.size() - 1);
        return;
    }
}
