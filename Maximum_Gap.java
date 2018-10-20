class Solution {
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int min = nums[0], max = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] < min) min = nums[i];
            if (nums[i] > max) max = nums[i];
        }
        if (max == min) return 0;
        int[] bucket1 = new int[nums.length + 1];
        int[] bucket2 = new int[bucket1.length];
        Arrays.fill(bucket1, Integer.MAX_VALUE);
        Arrays.fill(bucket2, Integer.MIN_VALUE);
        
        for (int n: nums) {
            int i = (int) (1.0 * (n - min) / (max - min) * nums.length);
            if (n < bucket1[i]) bucket1[i] = n;
            if (n > bucket2[i]) bucket2[i] = n;
        }
        int gap = 0, prev = 0, tmp;
        for (int i = 1; i < bucket1.length; ++i) {
            if (bucket1[i] < Integer.MAX_VALUE) {
                tmp = bucket1[i] - bucket2[prev];
                if (tmp > gap) gap = tmp;
                prev = i;
            }
        }
        return gap;
    }
}
