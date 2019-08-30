class Solution {
    
    private int dist(int[] worker, int[] bike) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }
    
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int W = workers.length, B = bikes.length;
        List<int[]>[] buckets = new ArrayList[2000];
        int d;
        for (int w = 0; w < W; ++w) {
            for (int b = 0; b < B; ++b) {
                d = dist(workers[w], bikes[b]);
                if (buckets[d] == null) buckets[d] = new ArrayList<>();
                buckets[d].add(new int[]{w, b});
            }
        }
        boolean[] busy = new boolean[W];
        boolean[] taken = new boolean[B];
        int[] result = new int[W];
        for (List<int[]> bucket: buckets) {
            if (bucket == null) continue;
            for (int[] pair: bucket) {
                if (!busy[pair[0]] && !taken[pair[1]]) {
                    busy[pair[0]] = true;
                    taken[pair[1]] = true;
                    result[pair[0]] = pair[1];
                }
            }
        }
        return result;
    }
}
