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
    private List<TreeNode> generateTreesInterval(int left, int right) {
        if (left > right) return Collections.emptyList();
        if (left == right)
            return Collections.singletonList(new TreeNode(left));
        List<TreeNode> trees = new ArrayList<>();
        for (int i = left; i <= right; ++i) {
            List<TreeNode> leftTrees = generateTreesInterval(left, i - 1);
            List<TreeNode> rightTrees = generateTreesInterval(i + 1, right);
            if (leftTrees.isEmpty()) {
                for (TreeNode rightChild: rightTrees) {
                    TreeNode root = new TreeNode(i);
                    root.right = rightChild;
                    trees.add(root);
                }
            }
            else if (rightTrees.isEmpty()) {
                for (TreeNode leftChild: leftTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftChild;
                    trees.add(root);
                }
            }
            else {
                for (TreeNode leftChild: leftTrees) {
                    for (TreeNode rightChild: rightTrees) {
                        TreeNode root = new TreeNode(i);
                        root.left = leftChild;
                        root.right = rightChild;
                        trees.add(root);
                    }
                }
            }
        }
        return trees;
    }


    public List<TreeNode> generateTrees(int n) {
        return generateTreesInterval(1, n);

    }

}
