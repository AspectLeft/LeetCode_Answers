class Solution {
    public int jump(int[] nums) {
        if (nums.length <= 1)
            return 0;
        int[] reach = new int[nums.length];
        int[] next = new int[nums.length];
        int[] border = new int[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            next[i] = nums[i] + i;
        }
        border[0] = next[0];
        for (int i = 1; i < nums.length; ++i) {
            border[i] = Math.max(border[i - 1], next[i]);
        }
        reach[0] = 0;
        int i = 0;
        while (true) {
            i += 1;
            reach[i] = border[reach[i - 1]];
            if (reach[i] >= nums.length - 1)
                return i;
        }
    }
}
