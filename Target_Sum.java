class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0) return 0;
        
        int sum = 0;
        for (int v: nums) sum += v;
        if (sum < S || (sum + S) % 2 != 0) return 0;
        
        List<Map<Integer, Integer>> mapList = new ArrayList<>();
        Map<Integer, Integer> initMap = new HashMap<>();
        initMap.put(nums[0], 1);
        initMap.put(-nums[0], initMap.getOrDefault(-nums[0], 0) + 1);
        mapList.add(initMap);
        for (int i = 1; i < nums.length; ++i) {
            Map<Integer, Integer> map = new HashMap<>();
            int plus, minus;
            for (Map.Entry<Integer, Integer> entry: mapList.get(i - 1).entrySet()) {
                plus = entry.getKey() + nums[i];
                minus = entry.getKey() - nums[i];
                map.put(plus, map.getOrDefault(plus, 0) + entry.getValue());
                map.put(minus, map.getOrDefault(minus, 0) + entry.getValue());
            }
            mapList.add(map);
        }
        return mapList.get(mapList.size() - 1).getOrDefault(S, 0);
    }
}
