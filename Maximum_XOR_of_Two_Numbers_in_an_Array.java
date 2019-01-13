class Solution {
    class Node {
        private Integer val;
        private Node zero, one;
    }
    
    private Node root;
    
    private void put(int n) {
        root = put(root, n, 30);
    }
    
    private Node put(Node x, int n, int d) {
        if (x == null) x = new Node();
        if (d == -1) { x.val = n; return x; }
        if ((n & (1 << d)) == 0)
            x.zero = put(x.zero, n, d - 1);
        else x.one = put(x.one, n, d - 1);
        return x;
    }
    
    private int get(int n) {
        return get(root, n, 30).val;
    }
    
    private Node get(Node x, int n, int d) {
        if (d == -1) return x;
        if ((n & (1 << d)) == 0) {
            if (x.zero != null)
                return get(x.zero, n, d - 1);
            else
                return get(x.one, n, d - 1);
        }
        else {
            if (x.one != null)
                return get(x.one, n, d - 1);
            else
                return get(x.zero, n, d - 1);
        }
            
    }
    
    public int findMaximumXOR(int[] nums) {
        for (int n: nums) put(n);
        int max = 0;
        for (int n: nums)
            max = Math.max(max, n ^ get(~n));
        return max;
    }
}
