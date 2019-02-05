class Solution {
    private String rangeToString(int a, int b) {
        if (a == b) return String.valueOf(a);
        return a + "->" + b;
    }
    
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> output = new ArrayList<>();
        if (nums.length == 0) {
            output.add(rangeToString(lower, upper));
            return output;
        }
        if (nums[0] > lower) {
            output.add(rangeToString(lower, nums[0] - 1));
        }
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] != nums[i - 1] && nums[i] > nums[i - 1] + 1) {
                output.add(rangeToString(nums[i - 1] + 1, nums[i] - 1));
            }
        }
        if (nums[nums.length - 1] < upper)
            output.add(rangeToString(nums[nums.length - 1] + 1, upper));
        return output;
    }
}
