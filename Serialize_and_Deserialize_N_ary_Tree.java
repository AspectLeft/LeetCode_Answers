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
class Codec {

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if (root == null) return null;
        StringBuilder builder = new StringBuilder();
        toString(root, builder);
        return builder.toString();
    }
    
    private void toString(Node root, StringBuilder builder) {
        builder.append(root.val);
        builder.append('[');
        for (Node child: root.children) {
            toString(child, builder);
        }
        builder.append(']');
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data == null) return null;
        int div0 = data.indexOf("[");
        int val = Integer.valueOf(data.substring(0, div0));
        List<Node> children = new ArrayList<>();
        Node root = new Node(val, children);
        int i = div0 + 1;
        int j = i;
        int balance;
        while (data.charAt(i) != ']') {
            j = i;
            while (data.charAt(j) != '[') j++;
            balance = 1;
            j++;
            while (balance != 0) {
                if (data.charAt(j) == '[') balance++;
                if (data.charAt(j) == ']') balance--;
                j++;
            }
            children.add(deserialize(data.substring(i, j)));
            i = j;
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
