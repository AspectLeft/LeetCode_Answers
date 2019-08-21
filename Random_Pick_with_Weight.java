class Solution {
    Random random;
    int[] preSum;
    public Solution(int[] w) {
        random = new Random();
        int sum = 0;
        preSum = new int[w.length];
        for (int i = 0; i < w.length; ++i) {
            sum += w[i];
            preSum[i] = sum;
        }
    }
    
    public int pickIndex() {
        int id = 1 + random.nextInt(preSum[preSum.length - 1]);
        int l = 0, r = preSum.length - 1, mid;
        while (l < r) {
            mid = (l + r) / 2;
            if (preSum[mid] < id) {
                l = mid + 1;
            }
            else {
                r = mid;
            }
        }
        return l;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */
