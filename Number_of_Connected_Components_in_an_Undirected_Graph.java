class Solution {
    int n;
    int[] parent;
    int count;
    int[] rank;
    
    private int find(int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }
    
    private void union(int p, int q) {
        int rp = find(p);
        int rq = find(q);
        if (rp == rq) return;
        
        count--;
        if (rank[rp] > rank[rq]) {
            parent[rq] = rp;
        }
        else if (rank[rp] < rank[rq]) {
            parent[rp] = rq;
        }
        else {
            rank[rp]++;
            parent[rq] = rp;
        }
    }
    
    public int countComponents(int n, int[][] edges) {
        this.n = n;
        this.parent = new int[n];
        for (int i = 0; i < n; ++i) {
            this.parent[i] = i;
        }
        this.rank = new int[n];
        this.count = n;
        for (int[] edge: edges) {
            union(edge[0], edge[1]);
        }
        return count;
    }
}
