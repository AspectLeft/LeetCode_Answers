class Solution {
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int max = 0;
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i] * 2 - 1;
            if (sum == 0){
                if (i + 1 > max) {
                    max = i + 1;
                }
            }
            else {
                Integer prev = map.get(sum);
                if (prev != null) {
                    max = Math.max(max, i - prev);
                }
                else {
                    map.put(sum, i);
                }
            }
            
        }
        return max;
    }
}
