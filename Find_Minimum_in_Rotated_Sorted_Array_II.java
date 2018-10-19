class Solution {
    private int find(int[] nums, int l, int r) {
        if (l + 1 == r) return nums[r];
        int mid = (l + r) / 2;
        if (nums[mid] >  nums[0])
            return find(nums, mid, r);
        else if (nums[mid] < nums[0])
            return find(nums, l, mid);
        else return Math.min(find(nums, l, mid), find(nums, mid, r));
    }
    
    public int findMin(int[] nums) {
        if (nums.length == 1 || nums[0] < nums[nums.length - 1]) return nums[0];
        return find(nums, 0, nums.length - 1);
    }
}
