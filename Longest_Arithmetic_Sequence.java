class Solution {
    public int longestArithSeqLength(int[] A) {
        if (A == null || A.length == 0) return 0;
        int n = A.length;
        int m = 20000;
        int[][] opt = new int[n][m];
        int d;
        int result = 0;
        for (int i = 1; i < n; ++i) {
            for (int prev = 0; prev < i; ++prev) {
                d = A[i] - A[prev] + 10000;
                opt[i][d] = Math.max(opt[i][d], opt[prev][d] + 1);
                result = Math.max(result, opt[i][d]);
            }
        }
        return result + 1;
    }
}
