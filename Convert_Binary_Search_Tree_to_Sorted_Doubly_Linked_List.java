/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/
class Solution {
    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        if (root.left == null && root.right == null) {
            root.left = root.right = root;
            return root;
        }
        if (root.left != null && root.right != null) {
            Node lhead = treeToDoublyList(root.left);
            Node rhead = treeToDoublyList(root.right);
            lhead.left.right = root;
            root.left = lhead.left;
            root.right = rhead;
            rhead.left.right = lhead;
            lhead.left = rhead.left;
            rhead.left = root;
            return lhead;
        }
        if (root.left != null) {
            Node lhead = treeToDoublyList(root.left);
            root.left = lhead.left;
            root.right = lhead;
            lhead.left.right = root;
            lhead.left = root;
            return lhead;
        }
        if (root.right != null) {
            Node rhead = treeToDoublyList(root.right);
            root.left = rhead.left;
            root.right = rhead;
            rhead.left.right = root;
            rhead.left = root;
            return root;
        }
        return root;
    }
}
