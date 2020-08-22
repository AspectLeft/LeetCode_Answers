class Solution {
    public boolean check(int[] nums, int k, double x) {
        double now = 0, last = 0;
        int i = 0;
        for (; i < k; ++i) {
            now += nums[i] - x;
        }
        if (now >= 0) return true;
        for (; i < nums.length; ++i) {
            now += nums[i] - x;
            last += nums[i - k] - x;
            
            if (last < 0) {
                now -= last;
                last = 0;
            }
            
            if (now >= 0) return true;
        }
        
        return false;
    }
    
    public double findMaxAverage(int[] nums, int k) {
        double l = Integer.MIN_VALUE, r = Integer.MAX_VALUE;
        while (r - l > 1e-5) {
            if (check(nums, k, (r + l) / 2)) {
                l = (r + l) / 2;
            }
            else {
                r = (r + l) / 2;
            }
        }
        
        return l;
    }
}
