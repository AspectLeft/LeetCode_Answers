class Solution {
    public int findPairs(int[] nums, int k) {
        if (k < 0) return 0;
        if (k == 0) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int v: nums) {
                map.put(v, map.getOrDefault(v, 0) + 1);
            }
            int count = 0;
            for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
                if (entry.getValue() > 1) count++;
            }
            return count;
        }
        Set<Integer> set = new HashSet<>();
        for (int v: nums) set.add(v);
        int count = 0;
        for (int v: set) {
            if (set.contains(v + k)) {
                count++;
            }
        }
        return count;
    }
}
