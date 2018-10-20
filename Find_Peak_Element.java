class Solution {
    
    private int findPeak(int[] nums, int l, int r) {
        if (l == r) return l;
        int mid = (l + r) / 2;
        if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) return mid;
        if (nums[mid] <= nums[mid - 1]) return findPeak(nums, l, mid);
        return findPeak(nums, mid, r);
    }
    
    
    public int findPeakElement(int[] nums) {
        if (nums.length == 1) return 0;
        if (nums[0] > nums[1]) return 0;
        if (nums[nums.length - 1] > nums[nums.length - 2]) return nums.length - 1;
        return findPeak(nums, 0, nums.length - 1);
    } 
}
