class Solution {
    class PQM {
        PriorityQueue<int[]> queue;
        
        private int[] max = null;
        
        public PQM(int k) {
            this.queue = new PriorityQueue<>(k, Comparator.comparingInt(o -> o[0]));
        }
        
        public void add(int[] o) {
            queue.add(o);
            
            if (max == null || o[0] > max[0]) {
                max = o;
            }
        }
        
        public int[] peek() {
            return queue.peek();
        }
        
        public int[] bottom() {
            return max;
        }
        
        public int[] poll() {
            int[] o = queue.poll();
            if (max == o) {
                max = null;
            }
            
            return o;
        }
    }
    
    private int k;
    
    private void updateRange(int[] range, int a, int b) {
        if (b - a < range[1] - range[0] || (a < range[0] && b - a == range[1] - range[0])) {
            range[0] = a;
            range[1] = b;
        }
    }
    
    public int[] smallestRange(List<List<Integer>> nums) {
        this.k = nums.size();
        
        PQM pqm = new PQM(k);
        
        for (int i = 0; i < nums.size(); ++i) {
            pqm.add(new int[]{nums.get(i).get(0), 0, i});
        }
        int[] range = {pqm.peek()[0], pqm.bottom()[0]};
        
        while (true) {
            int[] min = pqm.poll();
            if (min[1] + 1 == nums.get(min[2]).size()) break;
            pqm.add(new int[]{nums.get(min[2]).get(min[1] + 1), min[1] + 1, min[2]});
            
            updateRange(range, pqm.peek()[0], pqm.bottom()[0]);
        }
        
        return range;
    }
}
