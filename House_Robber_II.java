class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        int opt1 = 0, opt2 = nums[1], tmp;
        for (int i = 2; i < nums.length; ++i) {
            tmp = nums[i] + opt1;
            if (opt2 > tmp) tmp = opt2;
            opt1 = opt2;
            opt2 = tmp;
        }
        int max = opt2;
        opt1 = nums[0];
        opt2 = Math.max(opt1, nums[1]);
        for (int i = 2; i < nums.length - 1; ++i) {
            tmp = nums[i] + opt1;
            if (opt2 > tmp) tmp = opt2;
            opt1 = opt2;
            opt2 = tmp;
        }
        if (opt2 > max) max = opt2;
        return max;
    }
}
