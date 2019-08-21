class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length < 2) return false;
        if (k == Integer.MIN_VALUE) return false;
        if (k < 0) k = -k;
        if (k == 0) {
            for (int i = 1; i < nums.length; ++i) {
                if (nums[i] == 0 && nums[i - 1] == 0) return true;
            }
            return false;
        }
        if (k < 2) return true;
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0, v;
        for (int i = 0; i < nums.length; ++i) {
            v = nums[i];
            sum = (sum + v) % k;
            if (i > 0 && sum == 0) return true;
            Integer prev = map.get(sum);
            if (prev != null) {
                if (i - prev > 1) return true;
            }
            else {
                map.put(sum, i);
            }
        }
        return false;
    }
}
