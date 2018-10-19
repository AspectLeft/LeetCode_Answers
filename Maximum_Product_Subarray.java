class Solution {
    public int maxProduct(int[] nums) {
        int dp1 = nums[0], dp2 = nums[0], max = nums[0], tmp1, tmp2;
        for (int i = 1; i < nums.length; ++i) {
            tmp1 = dp1 * nums[i];
            tmp2 = dp2 * nums[i];
            dp1 = nums[i];
            dp2 = nums[i];
            if (tmp1 > dp1) dp1 = tmp1;
            if (tmp2 > dp1) dp1 = tmp2;
            if (tmp1 < dp2) dp2 = tmp1;
            if (tmp2 < dp2) dp2 = tmp2;
            if (dp1 > max) max = dp1;
        }
        return max;
    }
}
