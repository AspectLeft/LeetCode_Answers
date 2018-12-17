/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
    private int firstOrder(TreeNode root, List<Integer> list) {
        if (root == null) return 0;
        list.add(root.val);
        int l_size = firstOrder(root.left, list), r_size = firstOrder(root.right, list);
        list.add(l_size);
        return l_size + r_size + 1;
    } 
    

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        firstOrder(root, list);
        StringBuilder builder = new StringBuilder();
        for (int v: list) {
            builder.append(v);
            builder.append(",");
        }
        if (builder.length() > 0) builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
    
    private TreeNode buildTree(List<Integer> list, int l, int r) {
        if (l >= r) return null;
        TreeNode root = new TreeNode(list.get(l));
        int l_size = list.get(r);
        root.left = buildTree(list, l + 1, l + 2 * l_size);
        root.right = buildTree(list, l + 2 * l_size + 1, r - 1);
        return root;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) return null;
        List<Integer> list = new ArrayList<>();
        for (String s: data.split(",")) {
            list.add(Integer.parseInt(s));
        }
        return buildTree(list, 0, list.size() - 1);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
