class Solution {
    
    
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();
        Arrays.sort(nums);
        int[] prev = new int[nums.length];
        int[] size = new int[nums.length];
        prev[0] = 0;
        size[0] = 1;
        int tail = 0, maxSize = 1;
        for (int i = 1; i < nums.length; ++i) {
            prev[i] = i;
            size[i] = 0;
            for (int j = 0; j < i; ++j) {
                if (nums[i] % nums[j] == 0 && size[j] > size[i]) {
                    prev[i] = j;
                    size[i] = size[j];
                }
            }
            if (++size[i] > maxSize) {
                maxSize = size[i];
                tail = i;
            }
        }
        List<Integer> output = new ArrayList<>();
        while (prev[tail] != tail) {
            output.add(nums[tail]);
            tail = prev[tail];
        }
        output.add(nums[tail]);
        return output;
    }
}
