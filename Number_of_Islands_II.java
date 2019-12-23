class Solution {
    class Node {
        long value;
        int size;
        int rank;
        Node parent;
        Node(long v) {
            value = v;
            size = 1;
            rank = 0;
            parent = this;
        }
    }
    
    private long encode(long n, int x, int y) {
        return n * x + y;
    }
    
    Map<Long, Node> map = new HashMap<>();
    
    private int count = 0;
    
    private Node find(long k) {
        Node p = map.get(k);
        if (p == null) return null;
        while (p != p.parent) {
            p.parent = p.parent.parent;
            p = p.parent;
        }
        return p;
    }
    
    private void union(long p, long q) {
        Node rootP = find(p);
        Node rootQ = find(q);
        if (rootP == rootQ) return;
        if (rootP == null || rootQ == null) return;
        
        count--;
        int rankP = rootP.rank;
        int rankQ = rootQ.rank;
        if (rankP > rankQ) {
            rootQ.parent = rootP;
        }
        else if (rankP < rankQ) {
            rootP.parent = rootQ;
        }
        else {
            rootQ.parent = rootP;
            rootP.rank++;
        }
    }
    
    public boolean have(int k) {
        return map.get(k) != null;
    }
    
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> output = new ArrayList<>();
        for (int[] position: positions) {
            int x = position[0], y = position[1];
            long island = encode(n, x, y);
            if (map.containsKey(island)) {
                output.add(count);
                continue;
            }
            count++;
            map.put(island, new Node(island));
            if (x > 0) {
                union(island, encode(n, x - 1, y));
            }
            if (x < m - 1) {
                union(island, encode(n, x + 1, y));
            }
            if (y > 0) {
                union(island, encode(n, x, y - 1));
            }
            if (y < n - 1) {
                union(island, encode(n, x, y + 1));
            }
            output.add(count);
        }
        return output;
    }
}
