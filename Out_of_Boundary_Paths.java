class Solution {
    private static final int mod = 1000000007;
    
    private int[][][] dp;
    
    public int findPaths(int m, int n, int N, int i, int j) {
        dp = new int[m][n][N + 1];
        for (int[][] mat: dp) {
            for (int[] row: mat) {
                Arrays.fill(row, -1);
            }
        }
        int count = dfs(N, i, j);
        // System.out.println(dp[i][j][1]);
        // System.out.println(dp[i][j][2]);
        // System.out.println(dp[i][j][3]);
        return count;
    }
    
    private int dfs(int N, int i, int j) {
        if (dp[i][j][N] != -1) return dp[i][j][N];
        if (N == 0) {
            dp[i][j][N] = 0;
            return 0;
        }
        int count = 0;
        if (i == 0) count++;
        if (i == dp.length - 1) count++;
        if (j == 0) count++;
        if (j == dp[0].length - 1) count++;
        
        if (i > 0) {
            count += dfs(N - 1, i - 1, j);
            count %= mod;
        }
        if (i < dp.length - 1) {
            count += dfs(N - 1, i + 1, j);
            count %= mod;
        }
        if (j > 0) {
            count += dfs(N - 1, i, j - 1);
            count %= mod;
        }
        if (j < dp[0].length - 1) {
            count += dfs(N - 1, i, j + 1);
            count %= mod;
        }
        dp[i][j][N] = count;
        return count;
    }
}
