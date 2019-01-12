class Solution {
    
    
    
    public boolean canPartition(int[] nums) {
        int sum = 0;
        int n = nums.length;
        for (int v: nums) sum += v;
        if (sum % 2 != 0) return false;
        int half = sum / 2;
        boolean[] feasible = new boolean[half + 1];
        feasible[0] = true;
        for (int v: nums) {
            for (int s = half; s >= v; --s) {
                feasible[s] = feasible[s] || feasible[s - v];
            }
        }
        return feasible[half];
    }
}
