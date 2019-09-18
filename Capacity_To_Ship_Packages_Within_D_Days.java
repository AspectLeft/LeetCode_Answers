class Solution {
    public int shipWithinDays(int[] weights, int D) {
        int l = 1, r = sum(weights), mid;
        while (l < r) {
            mid = (l + r) / 2;
            if (feasible(weights, D, mid)) {
                r = mid;
            }
            else {
                l = mid + 1;
            }
        }
        return l;
    }
    
    private boolean feasible(int[] weights, int D, int capacity) {
        int i = 0;
        int bucket = 0;
        int d = 1;
        while (i < weights.length) {
            if (bucket + weights[i] <= capacity) {
                bucket += weights[i];
                i++;
            }
            else {
                d++;
                if (d > D) return false;
                bucket = 0;
            }
        }
        return true;
    }
    
    private int sum(int[] weights) {
        int s = 0;
        for (int w: weights) s += w;
        return s;
    }
}
