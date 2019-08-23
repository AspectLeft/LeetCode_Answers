class Solution {
    // 0: unknown, -1: searching, -2: 0
    int[][] mat;
    int[][] dist;
    int m, n;
    
    public int[][] updateMatrix(int[][] matrix) {
        mat = matrix;
        m = matrix.length;
        n = matrix[0].length;
        dist = new int[m][n];
        List<int[]> level = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (isBorder(i, j)) {
                    dist[i][j] = 1;
                    level.add(new int[]{i, j});
                }
            }
        }
        int d = 2;
        int x, y;
        while (!level.isEmpty()) {
            List<int[]> nextLevel = new ArrayList<>();
            for (int[] p: level) {
                x = p[0];
                y = p[1];
                if (x > 0 && mat[x - 1][y] == 1 && dist[x - 1][y] == 0) {
                    dist[x - 1][y] = d;
                    nextLevel.add(new int[]{x - 1, y});
                }
                if (x < m - 1 && mat[x + 1][y] == 1 && dist[x + 1][y] == 0) {
                    dist[x + 1][y] = d;
                    nextLevel.add(new int[]{x + 1, y});
                }
                if (y > 0 && mat[x][y - 1] == 1 && dist[x][y - 1] == 0) {
                    dist[x][y - 1] = d;
                    nextLevel.add(new int[]{x, y - 1});
                }
                if (y < n - 1 && mat[x][y + 1] == 1 && dist[x][y + 1] == 0) {
                    dist[x][y + 1] = d;
                    nextLevel.add(new int[]{x, y + 1});
                }
            }
            level = nextLevel;
            d++;
        }
        return dist;
    }
    
    private boolean isBorder(int i, int j) {
        if (mat[i][j] != 1) return false;
        if (i > 0 && mat[i - 1][j] == 0) return true;
        if (i < m - 1 && mat[i + 1][j] == 0) return true;
        if (j > 0 && mat[i][j - 1] == 0) return true;
        if (j < n - 1 && mat[i][j + 1] == 0) return true;
        return false;
    }
}
