class Solution {
    public int findLHS(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int v: nums) {
            map.put(v, map.getOrDefault(v, 0) + 1);
        }
        int max = 0;
        for (int v: map.keySet()) {
            if (map.containsKey(v - 1))
                max = Math.max(max, map.get(v) + map.get(v - 1));
        }
        return max;
    }
}
