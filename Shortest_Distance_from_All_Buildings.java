class Solution {
    class Pair {
        int x, y;
        
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    Set<Pair> buildings = new HashSet<>();
    
    int[][] distCountMap;
    int[][] reachableCount;
    
    int m, n;
    Pair[][] pairs;
    
    private boolean inBound(Pair pair, int[] dir) {
        return 0 <= pair.x + dir[0] && pair.x + dir[0] < m
            && 0 <= pair.y + dir[1] && pair.y + dir[1] < n;
    }
    
    private Pair getNeighbor(Pair pair, int[] dir) {
        return pairs[pair.x + dir[0]][pair.y + dir[1]];
    }
    
    private boolean isEmptyLand(Pair pair, int[][] grid) {
        return grid[pair.x][pair.y] == 0;
    }
    
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        
        m = grid.length;
        n = grid[0].length;
        pairs = new Pair[m][n];
        distCountMap = new int[m][n];
        reachableCount = new int[m][n];
        
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                pairs[i][j] = new Pair(i, j);
                if (grid[i][j] == 1) {
                    buildings.add(pairs[i][j]);
                }
            }
        }
        
        int[][] dirs = new int[][]{{-1, 0}, {+1, 0}, {0, -1}, {0, +1}};
        
        for (Pair building: buildings) {
            List<Pair> level = new ArrayList<>();
            boolean[][] visited = new boolean[m][n];
            int dist = 1;
            
            for (int[] dir: dirs) {
                if (inBound(building, dir)) {
                    Pair neighbor = getNeighbor(building, dir);
                    if (isEmptyLand(neighbor, grid)) {
                        level.add(neighbor);
                    }
                }
            }
            
            while (!level.isEmpty()) {
                List<Pair> nextLevel = new ArrayList<>();
                for (Pair front: level) {
                    if (visited[front.x][front.y]) continue;
                    visited[front.x][front.y] = true;
                    if (!isEmptyLand(front, grid)) continue;
                    
                    distCountMap[front.x][front.y] += dist;
                    reachableCount[front.x][front.y]++;
                    
                    for (int[] dir: dirs) {
                        if (inBound(front, dir)) {
                            nextLevel.add(getNeighbor(front, dir));
                        }
                    }
                }
                level = nextLevel;
                dist++;
            }
            
        }
        
        
        boolean feasible = false;
        int minDistSum = Integer.MAX_VALUE;
        for (int x = 0; x < m; ++x) {
            for (int y = 0; y < n; ++y) {
                if (grid[x][y] != 0) continue;
                if (reachableCount[x][y] != buildings.size()) continue;
                feasible = true;
                int distSum = distCountMap[x][y];
                if (distSum < minDistSum) minDistSum = distSum;
            }
        }
        if (!feasible) return -1;
        return minDistSum;
    }
}
