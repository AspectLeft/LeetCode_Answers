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
    
    public int closestValue(TreeNode root, double target) {
        double d = Math.abs(target - root.val);
        if (target > root.val) {
            if (root.right != null) {
                int r = closestValue(root.right, target);
                if (d < Math.abs(target - r)) {
                    return root.val;
                }
                else {
                    return r;
                }
            }
        }
        else {
            if (root.left != null) {
                int l = closestValue(root.left, target);
                if (d < Math.abs(target - l)){
                    return root.val;
                }
                else {
                    return l;
                }
            }
        }
        return root.val;
    }
    
    private TreeNode getPrev(Stack<TreeNode> lstack, TreeNode current) {
        if (current.left != null) {
            current = current.left;
            while (current.right != null) {
                lstack.push(current);
                current = current.right;
            }
            return current;
        }
        else {
            if (lstack.empty()) return null;
            else return lstack.pop();
        }
    }
    
    private TreeNode getNext(Stack<TreeNode> rstack, TreeNode current) {
        if (current.right != null) {
            current = current.right;
            while (current.left != null) {
                rstack.push(current);
                current = current.left;
            }
            return current;
        }
        else {
            if (rstack.empty()) return null;
            else return rstack.pop();
        }
    }
    
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        int closest = closestValue(root, target);
        Stack<TreeNode> lstack = new Stack<>();
        Stack<TreeNode> rstack = new Stack<>();
        TreeNode current = root;
        while (closest != current.val) {
            if (closest > current.val) {
                lstack.push(current);
                current = current.right;
            }
            else {
                rstack.push(current);
                current = current.left;
            }
        }
        List<Integer> output = new ArrayList<>();
        output.add(closest);
        TreeNode l = getPrev(lstack, current), r = getNext(rstack, current);
        while (output.size() < k) {
            if (l != null && r != null) {
                double d1 = Math.abs(target - l.val);
                double d2 = Math.abs(target - r.val);
                if (d1 < d2) {
                    output.add(l.val);
                    l = getPrev(lstack, l);
                }
                else {
                    output.add(r.val);
                    r = getNext(rstack, r);
                }
            }
            else if (l != null) {
                output.add(l.val);
                l = getPrev(lstack, l);
            }
            else {
                output.add(r.val);
                r = getNext(rstack, r);
            }
        }
        return output;
    }
}
