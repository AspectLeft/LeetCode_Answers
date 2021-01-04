class Solution {
    class Co {
        int x, y;
        int h;
        
        Co(int x, int y) {
            this.x = x;
            this.y = y;
            this.h = 0;
        }
        
        Co(int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
        }
        
        @Override
        public boolean equals(Object o) {
            // self check
            if (this == o)
                return true;
            // null check
            if (o == null)
                return false;
            // type check and cast
            if (getClass() != o.getClass())
                return false;
            Co co = (Co) o;
            // field comparison
            return this.x == co.x && this.y == co.y;
        }
        
        public boolean inBound(int m, int n) {
            return 0 <= x && x < m && 0 <= y && y < n;
        }
        
        @Override
        public String toString() {
            return String.format("x%dy%dh%d", x, y, h);
        }
    }
    
    public int cutOffTree(List<List<Integer>> forest) {
        if (forest == null) return 0;
        int m = forest.size();
        if (m == 0) return 0;
        int n = forest.get(0).size();
        if (n == 0) return 0;
        
        List<Co> trees = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (forest.get(i).get(j) > 1) {
                    trees.add(new Co(i, j, forest.get(i).get(j)));
                }
            }
        }
        
        Collections.sort(trees, Comparator.comparingInt(co -> co.h));
        // System.out.println(trees);
        
        
        
        
        Co s = new Co(0, 0, forest.get(0).get(0));
        
        int steps = 0, d;
        for (Co t: trees) {
            d = bfs(forest, s, t);
            if (d == -1) return -1;
            steps += d;
            // System.out.println(d);
            s = t;
        }
        return steps;
    }
    
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    
    private int bfs(List<List<Integer>> forest, Co s, Co t) {
        int m = forest.size(), n = forest.get(0).size();
        boolean[][] visited = new boolean[m][n];
        List<Co> level = new ArrayList<>();
        List<Co> nextLevel = new ArrayList<>();
        level.add(s);
        int d = 0;
        while (!level.isEmpty()) {
            nextLevel.clear();
            
            for (Co co: level) {
                visited[co.x][co.y] = true;
                if (co.equals(t)) {
                    return d;
                }
                for (int[] dir: DIRS) {
                    Co next = new Co(co.x + dir[0], co.y + dir[1]);
                    if (!next.inBound(m, n) || forest.get(next.x).get(next.y) == 0
                       || visited[next.x][next.y]) {
                        continue;
                    }
                    nextLevel.add(next);
                    visited[next.x][next.y] = true;
                }
            }
            List<Co> tmp = level;
            level = nextLevel;
            nextLevel = tmp;
            d++;
        }
        return -1;
    }
}
