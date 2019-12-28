class Solution {
    public boolean isReflected(int[][] points) {
        if (points == null || points.length == 0) return true;
        Set<String> set = new HashSet<>();
        int xl = points[0][0], xr = points[0][0];
        for (int[] point: points) {
            if (point[0] < xl) xl = point[0];
            if (point[0] > xr) xr = point[0];
            set.add(String.format("%d,%d", point[0], point[1]));
        }
        int xm2 = xl + xr;
        for (int[] point: points) {
            if (!set.contains(String.format("%d,%d", xm2 - point[0], point[1]))) return false;
        }
        return true;
    }
}
