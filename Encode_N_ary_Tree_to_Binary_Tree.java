/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Codec {

    // Encodes an n-ary tree to a binary tree.
    public TreeNode encode(Node root) {
        if (root == null) return null;
        TreeNode p = new TreeNode(root.val);
        if (root.children.isEmpty()) return p;
        TreeNode prev = encode(root.children.get(0));
        p.right = prev;
        for (int i = 1; i < root.children.size(); ++i) {
            TreeNode tn = encode(root.children.get(i));
            prev.left = tn;
            prev = tn;
        }
        return p;
    }

    // Decodes your binary tree to an n-ary tree.
    public Node decode(TreeNode root) {
        if (root == null) return null;
        Node node = new Node(root.val, new ArrayList<>());
        TreeNode tn = root.right;
        while (tn != null) {
            node.children.add(decode(tn));
            tn = tn.left;
        }
        return node;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(root));
