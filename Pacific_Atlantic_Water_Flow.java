class Solution {
    private int[][] mat;
    private int m, n;
    
    private boolean[][] pacific;
    private boolean[][] atlantic;
    
    private void dfs(boolean[][] grid, int i, int j) {
        grid[i][j] = true;
        if (i > 0 && !grid[i - 1][j] && mat[i - 1][j] >= mat[i][j])
            dfs(grid, i - 1, j);
        if (i < m - 1 && !grid[i + 1][j] && mat[i + 1][j] >= mat[i][j])
            dfs(grid, i + 1, j);
        if (j > 0 && !grid[i][j - 1] && mat[i][j - 1] >= mat[i][j])
            dfs(grid, i, j - 1);
        if (j < n - 1 && !grid[i][j + 1] && mat[i][j + 1] >= mat[i][j])
            dfs(grid, i, j + 1);
    }
    
    public List<int[]> pacificAtlantic(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return new ArrayList<>();
        mat = matrix;
        m = matrix.length;
        n = matrix[0].length;
        pacific = new boolean[m][n];
        atlantic = new boolean[m][n];
        /*
        Arrays.fill(pacific[0], true);
        Arrays.fill(atlantic[m - 1], true);
        for (int i = 1; i < m; ++i) {
            pacific[i][0] = true;
            atlantic[i][n - 1] = true;
        }
        */
        for (int j = 0; j < n; ++j) {
            dfs(pacific, 0, j);
            dfs(atlantic, m - 1, j);
        }
        for (int i = 1; i < m; ++i) {
            dfs(pacific, i, 0);
            dfs(atlantic, i - 1, n - 1);
        }
        
        List<int[]> output = new ArrayList<>();
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if (pacific[i][j] && atlantic[i][j])
                    output.add(new int[]{i, j});
        return output;
    }
}
