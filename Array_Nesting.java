class Solution {
    // Lasso is impossible, because for any i, exists j: nums[j] == i
    public int arrayNesting(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length, max = 0, p, id, tmp;
        for (int i = 0; i < n; ++i) {
            if (nums[i] < 0) continue;
            p = 0;
            id = i;
            while (nums[id] >= 0) {
                p++;
                tmp = nums[id];
                nums[id] = -1;
                id = tmp;
            }
            if (p > max) max = p;
        }
        return max;
    }
}
