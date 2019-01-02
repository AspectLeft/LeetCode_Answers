class Solution {
    public int getMoneyAmount(int n) {
        if (n == 1) return 0;
        if (n == 2) return 1;
        int[][] opt = new int[n + 1][n + 1];
        for (int l = 1; l <= n; ++l) opt[l][l] = 0;
        for (int l = 1; l < n; ++l) opt[l][l + 1] = l;
        for (int w = 3; w <= n; ++w) {
            for (int l = 1; l + w - 1 <= n; ++l) {
                int r = l + w - 1;
                opt[l][r] = l + opt[l + 1][r];
                for (int i = l + 1; i < r; ++i) {
                    int o = i + Math.max(opt[l][i - 1], opt[i + 1][r]);
                    if (o < opt[l][r]) opt[l][r] = o;
                }
            }
        }
        return opt[1][n];
    }
}
