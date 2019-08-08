class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) return 0;
        if (points.length == 1) return 1;
        Arrays.sort(points, Comparator.comparingInt(o -> o[1]));
        int shots = 1, x = points[0][1];
        for (int i = 1; i < points.length; ++i) {
            if (x < points[i][0]) {
                x = points[i][1];
                shots++;
            }
        }
        return shots;
    }
}
