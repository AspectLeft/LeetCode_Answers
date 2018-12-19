class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int length = nums.length;
        int[] opt = new int[length];
        opt[0] = 1;
        int tail;
        for (int i = 1; i < length; ++i) {
            tail = nums[i];
            opt[i] = 1;
            for (int j = 0; j < i; ++j) {
                if (nums[j] < tail)
                    opt[i] = Math.max(opt[i], opt[j] + 1);
            }
        }
        int m = 1;
        for (int v: opt)
            if (v > m)
                m = v;
        return m;
        
        /*
                 
        int[] dp = new int[nums.length];
        int len = 0;

        for(int x : nums) {
            int i = Arrays.binarySearch(dp, 0, len, x);
            if(i < 0) i = -(i + 1);
            dp[i] = x;
            if(i == len) len++;
        }

        return len;
        */
    }
}
