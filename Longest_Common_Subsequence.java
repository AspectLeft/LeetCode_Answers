class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null) return 0;
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int v = Math.max(dp[i + 1][j], dp[i][j + 1]);
                
                if (text1.charAt(i) == text2.charAt(j)) {
                    v = Math.max(v, 1 + dp[i][j]);
                }
                
                dp[i + 1][j + 1] = v;
            }
        }
        return dp[m][n];
    }
}
