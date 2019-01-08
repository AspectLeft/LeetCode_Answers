class Solution {
    private Map<String, Integer> V; // id
    
    
    private int[] parent;  // parent[i] = parent of i
    private byte[] rank;   // rank[i] = rank of subtree rooted at i (never more than 31)
    private int count;     // number of components
    
    private double[] ratio;
    
    class RootRatio {
        int root;
        double k;
        RootRatio(int r, double k) {
            root = r;
            this.k = k;
        }
    }
    
    private RootRatio find(int p) {
        double k = 1.0;
        while (p != parent[p]) {
            ratio[p] *= ratio[parent[p]];
            parent[p] = parent[parent[p]];
            k *= ratio[p];
            p = parent[p];
        }
        return new RootRatio(p, k);
    }
    
    
    public boolean connected(int p, int q) {
        return find(p).root == find(q).root;
    }
    
    
    public void union(int p, int q, double k) {
        RootRatio rrp = find(p), rrq = find(q);
        int rootP = rrp.root;
        int rootQ = rrq.root;
        k *= rrq.k / rrp.k;
        if (rootP == rootQ) return;

        // make root of smaller rank point to root of larger rank
        if      (rank[rootP] < rank[rootQ]) {
            parent[rootP] = rootQ;
            ratio[rootP] = k;
        }
        else if (rank[rootP] > rank[rootQ]) {
            parent[rootQ] = rootP;
            ratio[rootQ] = 1.0 / k;
        }
        else {
            parent[rootQ] = rootP;
            ratio[rootQ] = 1.0 / k;
            rank[rootP]++;
        }
        count--;
    }
    
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        V = new HashMap<>();
        
        int n = equations.length * 2 + 1;
        parent = new int[n];
        count = n;
        rank = new byte[n];
        ratio = new double[n];
        for (int i = 0; i < n; ++i) {
            parent[i] = i;
            rank[i] = 0;
            ratio[i] = 1.0;
        }
        
        int id = 0;
        for (int i = 0; i < equations.length; ++i) {
            String u = equations[i][0], v = equations[i][1];
            double k = values[i];
            if (!V.containsKey(u)) V.put(u, id++);
            if (!V.containsKey(v)) V.put(v, id++);
            union(V.get(u), V.get(v), k);
        }
        
        double[] output = new double[queries.length];
        for (int i = 0; i < output.length; ++i) {
            String u = queries[i][0], v = queries[i][1];
            if (!V.containsKey(u) || !V.containsKey(v)) {
                output[i] = -1.0;
                continue;
            }
            if (!connected(V.get(u), V.get(v))) {
                output[i] = -1.0;
                continue;
            }
            output[i] = find(V.get(u)).k / find(V.get(v)).k;
        }
        
        return output;
    }
}
