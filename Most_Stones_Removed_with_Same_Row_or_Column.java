class Solution {
    public int removeStones(int[][] stones) {
        if (stones.length < 2) return 0;
        Map<Integer, List<Integer>> xMap = new HashMap<>();
        Map<Integer, List<Integer>> yMap = new HashMap<>();
        int n = stones.length;
        int[] stone;
        List<Integer> ids;
        for (int i = 0, x, y; i < n; ++i) {
            stone = stones[i];
            x = stone[0];
            y = stone[1];
            
            ids = xMap.get(x);
            if (ids == null) {
                ids = new ArrayList<>();
                xMap.put(x, ids);
            }
            ids.add(i);
            
            ids = yMap.get(y);
            if (ids == null) {
                ids = new ArrayList<>();
                yMap.put(y, ids);
            }
            ids.add(i);
        }
        
        boolean[] dirty = new boolean[n];
        int islands = 0;
        int id;
        for (int i = 0; i < n; ++i) {
            if (dirty[i]) continue;
            islands++;
            
            Stack<Integer> stack = new Stack<>();
            stack.push(i);
            while (!stack.empty()) {
                id = stack.pop();
                dirty[id] = true;
                for (int adj: xMap.get(stones[id][0])) {
                    if (!dirty[adj]) {
                        stack.push(adj);
                    }
                }
                for (int adj: yMap.get(stones[id][1])) {
                    if (!dirty[adj]) {
                        stack.push(adj);
                    }
                }
            }
        }
        
        return n - islands;
    }
    
}
