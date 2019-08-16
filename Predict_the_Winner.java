class Solution {
    public boolean PredictTheWinner(int[] nums) {
        if (nums == null || nums.length % 2 == 0) return true;
        int n = nums.length;
        int[][] rangeSum = new int[n + 1][n + 1];
        for (int i = 0; i < n; ++i) {
            int sum = 0;
            int j;
            for (j = i; j < n; ++j) {
                rangeSum[i][j] = sum;
                sum += nums[j];
            }
            rangeSum[i][j] = sum;
        }
        int[][] opt = new int[n + 1][n + 1];
        for (int width = 1; width <= n; ++width) {
            for (int i = 0; i + width <= n; ++i) {
                opt[i][i + width] = Math.max(
                    nums[i] + (rangeSum[i + 1][i + width] - opt[i + 1][i + width]),
                    nums[i + width - 1] + (rangeSum[i][i + width - 1] - opt[i][i + width - 1]));
            }
        }
        return opt[0][n] * 2 >= rangeSum[0][n];
    }
}
