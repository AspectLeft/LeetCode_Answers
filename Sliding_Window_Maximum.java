class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[]{};
        Deque<Integer> indexDeque = new ArrayDeque<>();
        int n = nums.length;
        int[] output = new int[n - k + 1];
        int i, v;
        for (i = 0; i < k; ++i) {
            v = nums[i];
            while (!indexDeque.isEmpty() && nums[indexDeque.getLast()] <= v) indexDeque.removeLast();
            indexDeque.addLast(i);
        }
        output[0] = nums[indexDeque.getFirst()];
        for (; i < n; ++i) {
            if (indexDeque.getFirst() == i - k) indexDeque.removeFirst();
            v = nums[i];
            while (!indexDeque.isEmpty() && nums[indexDeque.getLast()] <= v) indexDeque.removeLast();
            indexDeque.addLast(i);
            output[i - k + 1] = nums[indexDeque.getFirst()];
        }
        return output;
    }
}
