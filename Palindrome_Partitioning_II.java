class Solution {
    boolean[][] isPalindrome;
    List<List<Integer>> leftIndices;
    public int minCut(String s) {
        if (s == null || s.equals("")) return 0;
        
        isPalindrome = new boolean[s.length()][s.length()];
        for (int sum = 0; sum <= 2 * (s.length() - 1); ++sum) {
            int l = sum / 2, r = (sum + 1) / 2;
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                isPalindrome[l][r] = true;
                l--;
                r++;
            }
        }
        
        int[] dp = new int[s.length()];
        dp[0] = 0;
        for (int r = 1; r < s.length(); ++r) {
            if (isPalindrome[0][r]) {
                dp[r] = 0;
                continue;
            }
            dp[r] = r;
            for (int l = 1; l <= r; ++l) {
                if (isPalindrome[l][r])
                    dp[r] = Math.min(dp[r], 1 + dp[l - 1]);
            }
        }
        
        return dp[s.length() - 1];
    }
    
}
