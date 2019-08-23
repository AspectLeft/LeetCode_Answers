class Solution {
    public int singleNonDuplicate(int[] nums) {
        if (nums.length == 1) return nums[0];
        int l = 0, r = nums.length - 1, mid;
        while (l < r) {
            mid = (l + r) / 2;
            if (mid % 2 == 0) {
                if (nums[mid] == nums[mid + 1]) {
                    l = mid + 1;
                }
                else {
                    r = mid;
                }
            }
            else {
                if (nums[mid] == nums[mid + 1]) {
                    r = mid;
                }
                else {
                    l = mid + 1;
                }
            }
        }
        return nums[l];
    }
}
