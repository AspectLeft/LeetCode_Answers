class Solution {
    int[] small = new int[]{0, 1, 4, 2, 5, 7};
    
    
    int[] dp = new int[10001];
    
    public int racecar(int t) {
        if (dp[t] != 0) return dp[t];
        int b = 1, n = 0;
        while (b <= t) {
            b <<= 1;
            n++;
        }
        if (b - 1 == t) {
            dp[t] = n;
            return dp[t];
        }
        dp[t] = n + 1 + racecar(b - 1 - t);
        for (int m = 0; m < n; ++m) {
            dp[t] = Math.min(dp[t], n - 1 + 1 + m + racecar(t - b / 2 + (1 << m)) + 1);
        }
        return dp[t];
    }
}
