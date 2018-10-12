class Solution {
    class Node {
        int value;
        int size;
        int rank;
        Node parent;
        Node(int v) {
            value = v;
            size = 1;
            rank = 0;
            parent = this;
        }
    }
    
    class UF {
        Map<Integer, Node> map;
        public UF(int[] nums) {
            map = new HashMap<>();
            for (int k: nums) {
                map.put(k, new Node(k));
            }
        }
        
        public Node find(int k) {
            Node p = map.get(k);
            while (p.parent != p) {
                p.parent = p.parent.parent;
                p = p.parent;
            }
            return p;
        }
        
        public boolean connected (int p, int q) {
            return find(p) == find(q);
        }
        
        public void union(int p, int q) {
            Node rootP = find(p);
            Node rootQ = find(q);
            if (rootP == rootQ) return;
            
            if (rootP.rank < rootQ.rank) {
                rootP.parent = rootQ;
                rootQ.size += rootP.size;
            } 
            else if (rootP.rank > rootQ.rank) {
                rootQ.parent = rootP;
                rootP.size += rootQ.size;
            }
            else {
                rootQ.parent = rootP;
                rootP.size += rootQ.size;
                rootP.rank++;
            }
        }
        
        public boolean have(int k) {
            return map.get(k) != null;
        }
        
        public int maxSet() {
            int max = 1;
            for (Node node: map.values()) {
                if (node.parent == node && node.size > max)
                    max = node.size;
            }
            return max;
        }
    }
    
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        UF uf = new UF(nums);
        for (int k: nums) {
            if (uf.have(k - 1))
                uf.union(k, k - 1);
            if (uf.have(k + 1))
                uf.union(k, k + 1);
        }
        return uf.maxSet();
    }
}
