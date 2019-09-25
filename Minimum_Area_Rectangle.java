class Solution {
    public int minAreaRect(int[][] points) {
        if (points.length < 4) return 0;
        int minArea = Integer.MAX_VALUE;
        Map<Integer, TreeSet<Integer>> xMap = new HashMap<>();
        Map<Integer, TreeSet<Integer>> yMap = new HashMap<>();
        for (int[] point: points) {
            TreeSet<Integer> ys = xMap.get(point[0]);
            TreeSet<Integer> xs = yMap.get(point[1]);
            if (ys == null) {
                ys = new TreeSet<>();
                xMap.put(point[0], ys);
            }
            if (xs == null) {
                xs = new TreeSet<>();
                yMap.put(point[1], xs);
            }
            
            ys.add(point[1]);
            xs.add(point[0]);
        }
        int x1, y1;
        int area;
        for (int[] p: points) {
            x1 = p[0];
            y1 = p[1];
            for (int x2: yMap.get(y1).tailSet(x1, false)) {
                if (x2 - x1 > minArea) break;
                for (int y2: xMap.get(x1).tailSet(y1, false)) {
                    if ((y2 - y1) * (x2 - x1) > minArea) break;
                    if (!xMap.get(x2).contains(y2)) continue;
                    area = (y2 - y1) * (x2 - x1);
                    if (area < minArea) minArea = area;
                    break;
                }
            }
        }
        return minArea == Integer.MAX_VALUE ? 0 : minArea;
    }
}
