class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        if (nums == null || nums.length < 3) return 0;
        Arrays.sort(nums);
        int result = 0;
        for (int i = 0; i < nums.length - 2; ++i) {
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                while (r > l && nums[l] + nums[r] + nums[i] >= target) {
                    r--;
                }
                if (r == l) break;
                result += r - l;
                l++;
            }
        }
        return result;
    }
}
