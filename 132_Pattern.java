class Solution {
    public boolean find132pattern(int[] nums) {
        if (nums == null || nums.length < 3) return false;
        int n = nums.length;
        int[] prevMin = new int[n];
        int min = nums[0];
        for (int i = 1; i < n; ++i) {
            prevMin[i] = min;
            if (nums[i] < min) min = nums[i];
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(nums[n - 1]);
        for (int i = n - 2; i > 0; --i) {
            min = prevMin[i];
            while (!stack.empty() && min >= stack.peek()) {
                stack.pop();
            }
            if (!stack.empty()) {
                if (min < nums[i] && nums[i] > stack.peek()) {
                    return true;
                }
            }
            stack.push(nums[i]);
        }
        return false;
    }
}
