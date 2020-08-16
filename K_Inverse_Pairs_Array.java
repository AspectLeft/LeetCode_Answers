class Solution {
    private static final int MOD = 1000000007;
    
    public int kInversePairs(int n, int k) {
        if (n <= 0) return 0;
        if (k < 0) return 0;
        if (k > n * (n - 1) / 2) return 0;
        if (k == 0) return 1;
        
        int[][] dp = new int[n + 1][k + 1];
        dp[1][0] = 1;
        
        int kk;
        
        for (int i = 2; i <= n; ++i) {
            dp[i][0] = 1;
            kk = Math.min(k, i * (i - 1) / 2);
            for (int j = 1; j <= kk; ++j) {
                dp[i][j] = ((dp[i][j - 1] + dp[i - 1][j]) % MOD +
                    + (j >= i ? (MOD - dp[i - 1][j - i]) : 0)) % MOD;
                
            }
        }
        
        return dp[n][k];
        
    }
}
