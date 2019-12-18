class Solution {
    int n;
    int[] rank;
    int[] parent;
    int count;
    
    private void init(int n) {
        this.n = n;
        this.count = n;
        rank = new int[n];
        parent = new int[n];
        for (int i = 0; i < n; ++i) {
            parent[i] = i;
        }
    }
    
    private int find(int i) {
        while (i != parent[i]) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }
    
    private boolean union(int i, int j) {
        i = find(i);
        j = find(j);
        if (i == j) return false;
        
        if (rank[i] > rank[j]) {
            parent[j] = i;
        }
        else if (rank[i] < rank[j]) {
            parent[i] = j;
        }
        else {
            parent[j] = i;
            rank[i]++;
        }
        count--;
        return true;
    }
    
    public boolean validTree(int n, int[][] edges) {
        if (n <= 0) return false;
        if (edges == null || edges.length != n - 1) return false;
        init(n);
        for (int[] edge: edges) {
            if (!union(edge[0], edge[1])) return false;
        }
        return true;
    }
}
