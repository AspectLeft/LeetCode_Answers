class Solution {
    int[] parent;
    int count;
    int n;
    int[] h;
    
    private int find(int i) {
        int p = parent[i];
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }
    
    private void union(int i, int j) {
        int p = find(i), q = find(j);
        if (p == q) return;
        
        if (h[p] > h[q]) {
            parent[q] = p;
        }
        else if (h[p] < h[q]) {
            parent[p] = q;
        }
        else {
            parent[p] = q;
            h[q]++;
        }
        count--;
    }
    
    public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0) return 0;
        n = M.length;
        count = n;
        parent = new int[n];
        h = new int[n];
        for (int i = 0; i < n; ++i) {
            parent[i] = i;
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (M[i][j] == 1) union(i, j);
            }
        }
        return count;
    }
}
