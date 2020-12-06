class TreeAncestor {
    // ancestor[node][p] := getKthAncestor(node, 2 ** p)
    private int[][] ancestor;

    public TreeAncestor(int n, int[] parent) {
        // n <= 5e4
        this.ancestor = new int[n][32];
        Arrays.fill(this.ancestor[0], -1);
        for (int node = 1; node < n; ++node) {
            this.ancestor[node][0] = parent[node];
            for (int p = 1; p < 32; ++p) {
                if (this.ancestor[node][p - 1] == -1) {
                    this.ancestor[node][p] = -1;
                    continue;
                }
                this.ancestor[node][p] = this.ancestor[this.ancestor[node][p - 1]][p - 1];
            }
        }
    }
    
    public int getKthAncestor(int node, int k) {
        if (node < 0) return -1;
        if (k == 0) return node;
        if (node == 0) return -1;
        int p = log2(k);
        return getKthAncestor(this.ancestor[node][p], k - (1 << p));
    }
    
    private int log2(int k) {
        int p = 0;
        while (k != 1) {
            k >>= 1;
            p++;
        }
        return p;
    }
}

/**
 * Your TreeAncestor object will be instantiated and called as such:
 * TreeAncestor obj = new TreeAncestor(n, parent);
 * int param_1 = obj.getKthAncestor(node,k);
 */
