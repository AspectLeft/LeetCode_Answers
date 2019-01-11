class Solution {
    private Map<javafx.util.Pair<Integer, Integer>, Integer> cache = new HashMap<>();
    private int m, n;
    private int[] sum;

    private int getOpt(int i, int j) {
        Integer value = cache.get(new javafx.util.Pair<>(i, j));
        if (value != null) return value;
        int l = j - 1, r = i - 1;
        while (l != r - 1) {
            int mid = (l + r) / 2;

            if (getOpt(mid, j - 1) > (sum[i] - sum[mid]))
                r = mid;
            else l = mid;
        }

        int opt1 = Math.max(getOpt(l, j - 1), sum[i] - sum[l]);
        int opt2 = Math.max(getOpt(r, j - 1), sum[i] - sum[r]);
        value = Math.min(opt1, opt2);
        cache.put(new javafx.util.Pair<>(i, j), value);
        return value;
    }

    public int splitArray(int[] nums, int m) {
        n = nums.length;
        this.m = m;

        sum = new int[n + 1];
        for (int i = 1; i <= n; ++i)
            sum[i] = sum[i - 1] + nums[i - 1];

        cache.put(new javafx.util.Pair<>(1, 1), nums[0]);
        for (int i = 2; i <= n; ++i) {
            cache.put(new javafx.util.Pair<>(i, 1), getOpt(i - 1, 1) + nums[i - 1]);
            cache.put(new javafx.util.Pair<>(i, i), Math.max(getOpt(i - 1, i - 1), nums[i - 1]));
        }
/*
        int[][] opt = new int[n + 1][m + 1];
        opt[1][1] = nums[0];
        for (int i = 2; i <= n; ++i) {
            opt[i][1] = opt[i - 1][1] + nums[i - 1];
            for (int j = 2; j <= m && j <= i; ++j) {
                if (j == i) {
                    opt[i][j] = Math.max(opt[i - 1][j - 1], nums[i - 1]);
                    continue;
                }
                int l = j - 1, r = i - 1;
                while (l != r - 1) {
                    int mid = (l + r) / 2;
                    if (opt[mid][j - 1] > (sum[i] - sum[mid]))
                        r = mid;
                    else l = mid;
                }
                int opt1 = Math.max(opt[l][j - 1], sum[i] - sum[l]);
                int opt2 = Math.max(opt[r][j - 1], sum[i] - sum[r]);
                opt[i][j] = Math.min(opt1, opt2);
            }
        }
        return opt[n][m];
        */


        return getOpt(n, m);
        //return 0;
    }
}
