class Solution {
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int[][] l = new int[m][n];
        int[][] r = new int[m][n];
        int[][] u = new int[m][n];
        int[][] d = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 'W') continue;
                if (j > 0) l[i][j] = l[i][j - 1];
                if (i > 0) u[i][j] = u[i - 1][j];
                if (grid[i][j] == 'E') {
                    l[i][j]++;
                    u[i][j]++;
                }
            }
        }
        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (grid[i][j] == 'W') continue;
                if (j < n - 1) r[i][j] = r[i][j + 1];
                if (i < m - 1) d[i][j] = d[i + 1][j];
                if (grid[i][j] == 'E') {
                    r[i][j]++;
                    d[i][j]++;
                }
            }
        }
        int max = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] != '0') continue;
                max = Math.max(max, l[i][j] + r[i][j] + u[i][j] + d[i][j]);
            }
        }
        // System.out.println(l[1][0]);
        // System.out.println(r[1][1]);
        // System.out.println(u[1][1]);
        // System.out.println(d[1][1]);
        return max;
    }
}
