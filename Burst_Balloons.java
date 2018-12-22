class Solution {
    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[][] coin = new int[n][n];
        for (int width = 1; width <= n; ++width) {
            for (int l = 0; l <= n - width; ++l) {
                int r = l + width - 1;
                for (int i = l; i <= r; ++i) {
                    coin[l][r] = Math.max(coin[l][r],
                                          (i - 1 >= l ? coin[l][i - 1] : 0)
                                          + (r >= i + 1 ? coin[i + 1][r] : 0)
                                          + (l > 0 ? nums[l - 1] : 1)
                                          * nums[i]
                                          * (r + 1 < n ? nums[r + 1] : 1));
                }
            }
        }
        return coin[0][n - 1];
    }
}
