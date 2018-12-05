class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int l = 0, r = 0;
        int sum = nums[0];
        int min = nums.length + 1;
        while (l < nums.length && r < nums.length) {
            if (sum >= s) {
                min = Math.min(min, r - l + 1);
                sum -= nums[l];
                l++;
            }
            else {
                r++;
                if (r == nums.length) break;
                sum += nums[r];
            }
        }
        if (min == nums.length + 1) return 0;
        return min;
    }
}
