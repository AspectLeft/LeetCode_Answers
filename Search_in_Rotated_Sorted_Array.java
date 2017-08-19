class Solution {
    
    private int find(int[] nums, int target, int m, int n) {
        if (m > n) return -1;
        int mid = (m + n) / 2;
        if (target == nums[mid])
            return mid;
        else if (target > nums[mid])
            return find(nums, target, mid + 1, n);
        else return find(nums, target, m, mid - 1);

    }

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        int length = nums.length;
        if (length == 1)
            return find(nums, target, 0, 0);
        if (nums[0] < nums[length - 1]) 
            return find(nums, target, 0, length - 1);
        int l = 0, r = nums.length - 1;
        int mid = 0;
        while (l < r) {
            mid = (l + r) / 2;
            if (nums[mid] > nums[length - 1]) {
                l = mid + 1;
            }
            else {
                r = mid;
            }
        }
        if (target < nums[0]) {
            return find(nums, target, l, length - 1);
        }
        else {
            return find(nums, target, 0, l - 1);
        }
    }

}
