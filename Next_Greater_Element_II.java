class Solution {
    public int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        Stack<Integer> stack = new Stack<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.empty() && nums[i] > nums[stack.peek()]) {
                stack.pop();
            }
            stack.push(i);
        }
        int[] output = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.empty() && nums[i] >= nums[stack.peek()]) stack.pop();
            if (!stack.empty()) output[i] = nums[stack.peek()];
            else output[i] = -1;
            stack.push(i);
        }
        return output;
    }
}
