/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class BSTIterator {
    
    private TreeNode current;
    private Stack<TreeNode> prev;

    public BSTIterator(TreeNode root) {
        current = root;
        prev = new Stack<>();
        while (current != null && current.left != null) {
            prev.push(current);
            current = current.left;
        } 
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return current != null;
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode output = current;
        if (current.right != null) {
            current = current.right;
            while (current.left != null) {
                prev.push(current);
                current = current.left;
            }
        }
        else {
            if (prev.empty()) current = null;
            else current = prev.pop();
        }
        return output.val;
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */
