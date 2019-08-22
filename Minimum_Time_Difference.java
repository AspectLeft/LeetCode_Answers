class Solution {
    public int findMinDifference(List<String> timePoints) {
        boolean[] table = new boolean[1440];
        int id;
        for (String t: timePoints) {
            id = refine(t);
            if (table[id]) return 0;
            table[id] = true;
        }
        int prev = 0;
        while (!table[prev]) prev++;
        int i = prev, start = prev;
        int minD = 1440;
        for (int j = 1; j < timePoints.size(); ++j) {
            i++;
            while (i < 1440 && !table[i]) i++;
            if (i == 1440) break;
            if (i - prev < minD) minD = i - prev;
            prev = i;
        }
        minD = Math.min(minD, 1440 - (i - start));
        return minD;
    }
    
    private int refine(String t) {
        String[] hm = t.split(":");
        return 60 * Integer.valueOf(hm[0]) + Integer.valueOf(hm[1]);
    }
}
