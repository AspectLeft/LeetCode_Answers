class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = nums[0], dp = max, length = nums.length;
        for (int i = 1; i < length; ++i) {
            dp = nums[i] + ((dp > 0) ? dp : 0);
            max = dp > max ? dp : max;
        }
        return max;
    }
}
