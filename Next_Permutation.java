class Solution {
    private void swap(int[] nums, int m, int n) {
        int tmp = nums[m];
        nums[m] = nums[n];
        nums[n] = tmp;
    }
    
    private void reverse(int[] nums, int m, int n) {
        for (int i = m; 2 * i < m + n; ++i) {
            swap(nums, i, m + n - i);
        }
    }

    public void nextPermutation(int[] nums) {
        int length = nums.length;
        if (length <= 1)
            return;
        int divide = -1;
        for (divide = nums.length - 1; divide > 0; --divide) {
            if (nums[divide - 1] < nums[divide]) {
                break;
            }
        }
        if (divide > 0) {
            int target = length - 1;
            while (nums[divide - 1] >= nums[target]) --target;
            swap(nums, divide - 1, target);
            reverse(nums, divide, length - 1);
        }
        else {
            reverse(nums, 0, length - 1);
        }
    }

}
