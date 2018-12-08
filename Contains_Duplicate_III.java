class Solution {
    class Node {
        int val;
        Node left, right;
        Node(int v) { val = v;}
    }
    
    private Node root = null;
    
    private void insert(int v) {
        root = insert(v, root);
    }
    
    private Node insert(int v, Node node) {
        if (node == null) return new Node(v);
        if (v == node.val) return node;
        if (v < node.val) 
            node.left = insert(v, node.left);
        else 
            node.right = insert(v, node.right);
        return node;
    }
    
    private void delete(int v) {
        root = delete(v, root);
    }
    
    private Node delete(int v, Node node) {
        if (node == null) return null;
        if (v == node.val) {
            if (node.left == null && node.right == null) {
                return null;
            }
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            Node lr = node.left;
            if (lr.right == null) {
                lr.right = node.right;
                return lr;
            }
            while (lr.right.right != null) {
                lr = lr.right;
            }
            Node newNode = lr.right;
            lr.right = newNode.left;
            newNode.left = node.left;
            newNode.right = node.right;
            return newNode;
        }
        if (v < node.val) {
            node.left = delete(v, node.left);
            return node;
        }
        else {
            node.right = delete(v, node.right);
            return node;
        }
    }
    
    private boolean searchNearby(int v, int t) {
        return searchNearby(v, t, root);
    }
    
    private boolean searchNearby(int v, int t, Node node) {
        if (node == null) return false;
        if (v > (long)(node.val) + t) return searchNearby(v, t, node.right);
        if (v < (long)(node.val) - t) return searchNearby(v, t, node.left);
        return true;
    }
    
    
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length <= 1) return false;
        int i;
        for (i = 0; i <= k && i < nums.length; ++i) {
            if (searchNearby(nums[i], t)) return true;
            insert(nums[i]);
        }
        for (; i < nums.length; ++i) {
            delete(nums[i - k - 1]);
            if (searchNearby(nums[i], t)) return true;
            insert(nums[i]);
        }
        return false;
    }
}
