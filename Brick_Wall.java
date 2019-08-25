class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> map = new HashMap<>();
        int slit;
        for (List<Integer> row: wall) {
            slit = 0;
            for (int i = 0, brick; i < row.size() - 1; ++i) {
                brick = row.get(i);
                slit += brick;
                map.put(slit, map.getOrDefault(slit, 0) + 1);
            }
        }
        int max = 0;
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
            }
        }
        return wall.size() - max;
    }
}
