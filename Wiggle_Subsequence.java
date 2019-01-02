class Solution {
    public int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 1;
        boolean trend = false;
        int output = 1;
        int i = 1;
        while (i < nums.length && nums[i] == nums[i - 1]) i++;
        if (i < nums.length) {
            trend = nums[i] > nums[i - 1];
            i++;
            output = 2;
            for (; i < nums.length; ++i) {
                if (trend) { // increasing
                    if (nums[i] < nums[i - 1]) {
                        output++;
                        trend = false;
                    }
                }
                else {
                    if (nums[i] > nums[i - 1]) {
                        output++;
                        trend = true;
                    }
                }
            }
        }
        return output;
    }
}
