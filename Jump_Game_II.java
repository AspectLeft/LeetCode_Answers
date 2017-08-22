class Solution {
    public int jump(int[] nums) {
        
        if (nums.length <= 1)
            return 0;
        int[] reach = new int[nums.length];
        int[] next = new int[nums.length];
        next[0] = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            next[i] = Math.max(next[i - 1], nums[i] + i);
        }
        reach[0] = 0;
        int i = 0;
        while (true) {
            i += 1;
            reach[i] = next[reach[i - 1]];
            if (reach[i] >= nums.length - 1)
                return i;
        }
    }
}
