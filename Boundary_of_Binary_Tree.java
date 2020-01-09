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
    private LinkedList<TreeNode> getLeftBoundary(TreeNode root) {
        LinkedList<TreeNode> leftBoundary = new LinkedList<>();
        if (root.left == null) return leftBoundary;
        TreeNode p = root.left;
        while (p != null) {
            leftBoundary.addLast(p);
            if (p.left != null) p = p.left;
            else p = p.right;
        }
        return leftBoundary;
    }
    
    private void getLeaves(TreeNode root, LinkedList<TreeNode> leaves) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            leaves.add(root);
            return;
        }
        getLeaves(root.left, leaves);
        getLeaves(root.right, leaves);
    }
    
    private LinkedList<TreeNode> getRightBoundary(TreeNode root) {
        
        LinkedList<TreeNode> rightBoundary = new LinkedList<>();
        if (root.right == null) return rightBoundary;
        TreeNode p = root.right;
        while (p != null) {
            rightBoundary.addFirst(p);
            if (p.right != null) p = p.right;
            else p = p.left;
        }
        return rightBoundary;
    }
    
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if (root == null) return new ArrayList<>();
        LinkedList<TreeNode> boundary = new LinkedList<>();
        boundary.add(root);
        boundary.addAll(getLeftBoundary(root));
        LinkedList<TreeNode> leaves = new LinkedList<>();
        getLeaves(root, leaves);
        if (boundary.getLast() == leaves.getFirst()) leaves.removeFirst();
        boundary.addAll(leaves);
        LinkedList<TreeNode> rightBoundary = getRightBoundary(root);
        if (!rightBoundary.isEmpty() 
            && rightBoundary.getFirst() == boundary.getLast()) {
            rightBoundary.removeFirst();
        }
        boundary.addAll(rightBoundary);
        
        List<Integer> output = new ArrayList<>();
        for (TreeNode node: boundary) {
            output.add(node.val);
        }
        
        return output;
    }
}
