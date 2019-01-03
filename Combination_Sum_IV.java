class Solution {
    private int output = 0;
    private int[] nums;
    private int target;
    
    private int[] opt;
    
    
    private int dp(int t) {
        if (opt[t] >= 0) return opt[t];
        opt[t] = 0;
        for (int i = 0; i < nums.length && nums[i] <= t; ++i)
            opt[t] += dp(t - nums[i]);
        return opt[t];
    }

    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        if (target <= 0) return 0;
        Arrays.sort(nums);
        this.nums = nums;
        this.target = target;

        opt = new int[target + 1];
        Arrays.fill(opt, -1);
        opt[0] = 1;


        return dp(target);
    }

}
