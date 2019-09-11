class Solution {
    private int m, n;
    private int[][] grid;
    
    
    private int expand(int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n) return 0;
        if (grid[i][j] == 1) {
            grid[i][j] = 0;
            return 1 + expand(i - 1, j) + expand(i + 1, j) + expand(i, j - 1) + expand(i, j + 1);
        }
        return 0;
    }
    
    
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        int maxArea = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                maxArea = Math.max(maxArea, expand(i, j));
            }
        }
        return maxArea;
    }
}
