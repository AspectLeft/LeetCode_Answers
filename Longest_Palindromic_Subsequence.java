class Solution {
    // Reduce to Longest Common Subseq: LCS(s, s.reverse())
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] a = s.toCharArray();
        int n = a.length;
        int[][] opt = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (a[i] == a[j]) {
                    opt[i][j] = 1 + ((i > 0 && j < n - 1) ? opt[i - 1][j + 1] : 0);
                }
                if (i > 0) {
                    opt[i][j] = Math.max(opt[i][j], opt[i - 1][j]);
                }
                if (j < n - 1) {
                    opt[i][j] = Math.max(opt[i][j], opt[i][j + 1]);
                }
            }
        }
        return opt[n - 1][0];
    }
}
