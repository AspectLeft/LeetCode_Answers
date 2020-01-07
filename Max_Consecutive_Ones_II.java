class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = 1;
        int opt1 = 0, opt2 = 1;
        if (nums[0] == 1) opt1 = opt2 = max = 1;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] == 1) opt2++;
            else opt2 = opt1 + 1;
            if (opt2 > max) max = opt2;
                
                
            if (nums[i] == 1) opt1++;
            else opt1 = 0;
            if (opt1 > max) max = opt1;
            
        }
        return max;
    }
}
