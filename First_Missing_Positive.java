class Solution {
    
    private void swap(int[] nums, int m, int n) {
        if (m != n) {
            int tmp = nums[m];
            nums[m] = nums[n];
            nums[n] = tmp;
        }
    }
    
    public int firstMissingPositive(int[] nums) {
        if (nums.length == 0)
            return 1;
        for (int i = 0; i < nums.length; ++i) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != i + 1)
                return i + 1;
        }

        return nums.length + 1;
    }
}
