class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        int[][] bottomHP = new int[m][n];
        bottomHP[m - 1][n - 1] = dungeon[m - 1][n - 1] >= 0 ? 1 : (1 - dungeon[m - 1][n - 1]);
        for (int j = n - 2; j >= 0; --j)
            bottomHP[m - 1][j] = Math.max(1, bottomHP[m - 1][j + 1] - dungeon[m - 1][j]);
        for (int i = m - 2; i >= 0; --i)
            bottomHP[i][n - 1] = Math.max(1, bottomHP[i + 1][n - 1] - dungeon[i][n - 1]);
        for (int i = m - 2; i >= 0; --i)
            for (int j = n - 2; j >= 0; --j)
                bottomHP[i][j] = Math.max(1, Math.min(bottomHP[i][j + 1], bottomHP[i + 1][j]) - dungeon[i][j]);
        return bottomHP[0][0];
    }
}
