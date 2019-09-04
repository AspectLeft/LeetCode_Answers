class Solution {
    private static final long m = 1000000000 + 7;
    
    public int numMusicPlaylists(int N, int L, int K) {
        long[][] opt = new long[L + 1][N + 1];
        opt[1][1] = 1;
        for (int l = 2; l <= L; ++l) {
            for (int n = 1; n <= N; ++n) {
                opt[l][n] = opt[l - 1][n] * (n - Math.min(l - 1, K));
                opt[l][n] = (opt[l][n] + opt[l - 1][n - 1] * n) % m;
                //System.out.println(String.format("opt[%d][%d]=%d", l, n, opt[l][n]));
            }
        }
        return (int)(opt[L][N]);
    }
}
