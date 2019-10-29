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
    public List<TreeNode> allPossibleFBT(int N) {
        if (N % 2 == 0) return new ArrayList<>();
        List<List<TreeNode>> dp = new ArrayList<>();
        List<TreeNode> init = new ArrayList<>();
        init.add(new TreeNode(0));
        dp.add(init);
        for (int size = 3; size <= N; size += 2) {
            List<TreeNode> list = new ArrayList<>();
            int l, r;
            for (l = 1; l < size; l += 2) {
                r = size - 1 - l;
                //System.out.println(l);
                //System.out.println(r);
                List<TreeNode> leftTrees = dp.get(l / 2);
                List<TreeNode> rightTrees = dp.get(r / 2);
                for (TreeNode leftTree: leftTrees) {
                    for (TreeNode rightTree: rightTrees) {
                        TreeNode root = new TreeNode(0);
                        root.left = leftTree;
                        root.right = rightTree;
                        list.add(root);
                    }
                }
            }
            dp.add(list);
        }
        return dp.get(dp.size() - 1);
    }
}
