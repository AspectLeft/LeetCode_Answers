class Solution {
    Set<List<Integer>> dirty;
    int r, c;
    int range;
    Random random;
    
    public Solution(int n_rows, int n_cols) {
        r = n_rows;
        c = n_cols;
        dirty = new HashSet<>();
        range = n_rows * n_cols;
        random = new Random();
        
    }
    
    public int[] flip() {
        int id = random.nextInt(range);
        range--;
        List<Integer> p = new ArrayList<>();
        p.add(id / c);
        p.add(id % c);
        int prev = 0;
        for (List<Integer> q: dirty) {
            if (toId(q) <= toId(p)) {
                prev++;
            }
        }
        while (prev > 0) {
            while (dirty.contains(p)) {
                p.set(1, p.get(1) + 1);
                if (p.get(1) == c) {
                    p.set(1, 0);
                    p.set(0, p.get(0) + 1);
                }
            }
            prev--;
        }
        dirty.add(p);
        return new int[]{p.get(0), p.get(1)};
    }
    
    public void reset() {
        dirty.clear();
        range = r * c;
    }
    
    private int toId(List<Integer> p) {
        return p.get(0) * c + p.get(1);
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(n_rows, n_cols);
 * int[] param_1 = obj.flip();
 * obj.reset();
 */
