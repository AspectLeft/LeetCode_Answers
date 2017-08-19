class Solution {
    public int[] searchRangeMN(int[] nums, int target, int m, int n) {
        if (m > n)
            return new int[]{-1, -1};
        if (nums[m] > target || nums[n] < target)
            return new int[]{-1, -1};
        if (target == nums[m] && target == nums[n])
            return new int[]{m, n};

        int p = (2 * m + n) / 3, q = (m + 2 * n) / 3;
        if (p == q) {
            if (target == nums[m])
                return new int[]{m, m};
            if (target == nums[n])
                return new int[]{n, n};
            return new int[]{-1, -1};            
        }
        
        if (target < nums[p])
            return searchRangeMN(nums, target, m, p - 1);
        if (target == nums[p]) {
            if (target < nums[q])
                return searchRangeMN(nums, target, m, q - 1);
            
            return new int[]{searchRangeMN(nums, target, m, p)[0], searchRangeMN(nums, target, q, n)[1]};
        }

        if (target < nums[q])
            return searchRangeMN(nums, target, p + 1, q - 1);
        if (target == nums[q])
            return searchRangeMN(nums, target, p + 1, n);
        return searchRangeMN(nums, target, q + 1, n);
    }

    public int[] searchRange(int[] nums, int target) {
        if (nums == null)
            return new int[]{-1, -1};
        return searchRangeMN(nums, target, 0, nums.length - 1);
    }
}
