class Solution {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> counter = new HashMap<>();
        Map<Integer, Integer> firstIndexMap = new HashMap<>();
        Map<Integer, Integer> lastIndexMap = new HashMap<>();
        
        for (int i = 0; i < nums.length; ++i) {
            int v = nums[i];
            Integer c = counter.get(v);
            if (c == null) {
                counter.put(v, 1);
                firstIndexMap.put(v, i);
                lastIndexMap.put(v, i);
            }
            else {
                counter.put(v, c + 1);
                lastIndexMap.put(v, i);
            }
        }
        int target = 1;
        for (int c: counter.values())
            if (c > target) target = c;
        int output = nums.length;
        for (int v: counter.keySet()) {
            if (counter.get(v) == target) {
                output = Math.min(output, lastIndexMap.get(v) - firstIndexMap.get(v) + 1);
            }
        }
        return output;
    }
}
