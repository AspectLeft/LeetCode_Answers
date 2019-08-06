class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();
        int n = nums.length;
        List<Integer> results = new ArrayList<>();
        int value;
        for (int i = 0; i < n; ++i) {
            value = nums[i];
            while (value > n) value -= n;
            nums[value - 1] += n;
        }
        for (int i = 0; i < n; ++i) {
            if (nums[i] > 2 * n) {
                results.add(i + 1);
            }
        }
        return results;
    }
}
