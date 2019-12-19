class Solution {
    public int numWays(int n, int k) {
        if (n == 0 || k == 0) return 0;
        if (n == 1) return k;
        int[] opt = new int[n];
        opt[0] = k;
        opt[1] = k * k;
        for (int i = 2; i < n; ++i) {
            opt[i] = (k - 1) * (opt[i - 1] + opt[i - 2]);
        }
        return opt[n - 1];
    }
}
