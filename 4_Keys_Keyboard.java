class Solution {
    private int pow3(int p) {
        int v = 1;
        for (int i = 0; i < p; ++i) v *= 3;
        return v;
    }
    
    public int maxA(int N) {
        if (N < 7) return N;
        int[] dp = new int[N + 1];
        int i = 1;
        for (; i <= 6; ++i) {
            dp[i] = i;
        }
        for (; i <= N; ++i) {
            dp[i] = Math.max(i, Math.max(5 * dp[i - 6], 
                                         Math.max(2 * dp[i - 3], 
                                                  Math.max(3 * dp[i - 4], 4 * dp[i - 5]))));
        }
        
        return dp[N];
    }
}
