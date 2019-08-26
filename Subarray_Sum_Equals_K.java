class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> presum = new HashMap<>();
        int sum = 0;
        int result = 0;
        for (int v: nums) {
            sum += v;
            if (sum == k) result++;
            result += presum.getOrDefault(sum - k, 0);
            presum.put(sum, presum.getOrDefault(sum, 0) + 1);
        }
        return result;
    }
}
