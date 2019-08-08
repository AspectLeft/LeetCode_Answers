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

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return serializeHelper(new StringBuilder(), root).toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) return null;
        String[] valueStrArray = data.split(",");
        int[] arr = new int[valueStrArray.length];
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = Integer.valueOf(valueStrArray[i]);
        }
        return deserializeHelper(arr, 0, arr.length - 1);
    }
    
    private StringBuilder serializeHelper(StringBuilder builder, TreeNode root) {
        if (root == null) return builder;
        if (builder.length() > 0) {
            builder.append(',');
        }
        builder.append(root.val);
        serializeHelper(builder, root.left);
        serializeHelper(builder, root.right);
        return builder;
    }
    
    private TreeNode deserializeHelper(int[] arr, int l, int r) {
        if (l > r) return null;
        TreeNode root = new TreeNode(arr[l]);
        int ll = l + 1, rr = r;
        if (ll > rr) return root;
        int mid;
        while (ll < rr) {
            mid = (ll + rr + 1) / 2;
            if (arr[mid] > root.val) {
                rr = mid - 1;
            }
            else {
                ll = mid;
            }
        }
        if (arr[ll] > root.val) {
            mid = ll;
        }
        else {
            mid = ll + 1;
        }
        root.left = deserializeHelper(arr, l + 1, mid - 1);
        root.right = deserializeHelper(arr, mid, r);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
