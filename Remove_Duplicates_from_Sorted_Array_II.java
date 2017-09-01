class Solution {
    public int removeDuplicates(int[] nums) {
        
        int r = 0;
        int dup = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (i == 0) {
                nums[r++] = nums[i];
            }
            else if (nums[i] == nums[i - 1]) {
                if (++dup < 2) {
                    nums[r++] = nums[i];
                }
            }
            else {
                dup = 0;
                nums[r++] = nums[i];
            }
        }
        return r;
    }
}
