class Solution {
    public int mergeStones(int[] stones, int k) {
        int n = stones.length;
        if ((n - 1) % (k - 1) != 0) return -1;
        if (n == 1) return 0;
        int[][] sum = new int[n + 1][n + 1];
        for (int i = 0; i < n; ++i) {
            int s = 0;
            int j;
            for (j = i; j < n; ++j) {
                sum[i][j] = s;
                s += stones[j];
            }
            sum[i][j] = s;
        }
        int T = (n - 1) / (k - 1);
        // opt[i][j][t]: minimum cost of doing t merges in stones[i (in), j (ex)]
        int[][][] opt = new int[n + 1][n + 1][T + 1];
        for (int i = 0; i + k <= n; ++i) {
            opt[i][i + k][1] = sum[i][i + k];
        }

        for (int l = k; l <= n; ++l) {
            for (int i = 0, j; i + l <= n; ++i) {
                j = i + l;
                int t;
                for (t = 1; 1 + t * (k - 1) < l; ++t) {
                    opt[i][j][t] = Integer.MAX_VALUE;
                    int t1, t2;
                    for (t1 = 1, t2 = t - t1; t1 < t; t1++, t2--) {
                        for (int i1 = i, i2; i1 + t * (k - 1) + 1 < j; ++i1) {
                            i2 = i1 + t1 * (k - 1) + 1;
                            if (i == 0 && j == n && t == 2) {
                                //System.out.println(String.format("%d %d", i1, i2));
                            }
                            opt[i][j][t] = Math.min(opt[i][j][t],
                                    opt[i1][i2][t1]
                                            + opt[i2][j][t2]
                            );
                            //System.out.println(String.format("opt[%d][%d][%d]=%d", i, j, t, opt[i][j][t]));

                        }
                    }
                    for (int i1 = i; i1 + t * (k - 1) + 1 <= j; ++i1) {
                        opt[i][j][t] = Math.min(opt[i][j][t],
                                opt[i1][i1 + t * (k - 1) + 1][t]);
                        //System.out.println(String.format("opt[%d][%d][%d]=%d", i, j, t, opt[i][j][t]));

                    }
                }
                if (1 + t * (k - 1) == l) {
                    opt[i][j][t] = sum[i][j] + opt[i][j][t - 1];
                    //System.out.println(String.format("opt[%d][%d][%d]=%d", i, j, t, opt[i][j][t]));

                }
            }
        }

        //printOpt(opt, n);
        return opt[0][n][(n - 1) / (k - 1)];
    }
}
