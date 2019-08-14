class Solution {
    public boolean makesquare(int[] nums) {
        if (nums == null || nums.length < 4) return false;
        int sum = 0;
        
        for (int v: nums) sum += v;
        if (sum % 4 != 0) return false;
        int a = sum / 4;
        Arrays.sort(nums);
        return dfs(a, a, a, a, nums, nums.length - 1);
    }
    
    private boolean dfs(int a, int b, int c, int d, int[] nums, int i) {
        if (a < 0 || b < 0 || c < 0 || d < 0) return false;
        if (a == 0 && b == 0 && c == 0 && d == 0) return true;
        int v = nums[i];
        return dfs(a - v, b, c, d, nums, i - 1)
            || dfs(a, b - v, c, d, nums, i - 1)
            || dfs(a, b, c - v, d, nums, i - 1)
            || dfs(a, b, c, d - v, nums, i - 1);
    }
}
