class Solution {
    
    public int nthSuperUglyNumber(int n, int[] primes) {
        if (n == 1) return 1;
        int way = primes.length;
        long[] opt = new long[n + 1];
        opt[1] = 1;
        int[] indices = new int[way];
        Arrays.fill(indices, 1);
        for (int i = 2; i <= n; ++i) {
            long[] candidates = new long[way];
            long candidate = Long.MAX_VALUE;
            for (int j = 0; j < way; ++j) {
                candidates[j] = opt[indices[j]] * primes[j];
                if (candidates[j] < candidate) candidate = candidates[j];
            }
            opt[i] = candidate;
            for (int j = 0; j < way; ++j) {
                if (candidates[j] == candidate) {
                    indices[j]++;
                }
            }
        }
        return (int) (opt[n]);
    }
}
