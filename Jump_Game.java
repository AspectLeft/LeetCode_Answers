class Solution {
    public boolean canJump(int[] nums) {
        
        if (nums.length <= 1)
            return true;
        int r = 0;
        for (int i = 0; i <= r && i < nums.length; ++i) {
            r = Math.max(r, nums[i] + i);
            if (r >= nums.length - 1)
                return true;
        }
        return r >= nums.length - 1;
    }
}
