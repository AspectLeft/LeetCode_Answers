class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        if (strs == null || strs.length == 0) return 0;
        int length = strs.length;
        int[][] cost = new int[length][2];
        String str;
        for (int i = 0; i < length; ++i) {
            str = strs[i];
            if (str != null && str.length() > 0) {
                int zero = 0, one = 0;
                for (char c: str.toCharArray()) {
                    if (c == '0') zero++;
                    if (c == '1') one++;
                }
                cost[i][0] = zero;
                cost[i][1] = one;
            }
        }
        int[][][] opt = new int[length][m + 1][n + 1];
        for (int i = 0; i < length; ++i) {
            for (int j = 0; j <= m; ++j) {
                for (int k = 0; k <= n; ++k) {
                    if (i > 0) opt[i][j][k] = opt[i - 1][j][k];
                    if (j >= cost[i][0] && k >= cost[i][1]) {
                        if (i == 0) 
                            opt[i][j][k] = 1;
                        else
                            opt[i][j][k] = Math.max(opt[i][j][k], 
                                                    1 + opt[i - 1][j - cost[i][0]][k - cost[i][1]]);
                    }
                }
            }
        }
        return opt[length - 1][m][n];
    }
}
