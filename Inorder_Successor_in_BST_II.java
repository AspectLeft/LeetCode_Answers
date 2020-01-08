/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};
*/
class Solution {
    public Node inorderSuccessor(Node x) {
        if (x.right != null) {
            Node p = x.right;
            while (p.left != null) p = p.left;
            return p;
        }
        if (x.parent == null) return null;
        Node p = x.parent, q = x;
        while (p != null && q == p.right) {
            q = p;
            p = p.parent;
        }
        return p;
    }
}
