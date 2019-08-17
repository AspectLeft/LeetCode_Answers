class Solution {
    int[] sum;
    int[][] rects;
    Random random;

    public Solution(int[][] rects) {
        this.sum = new int[rects.length];
        this.rects = rects;
        this.random = new Random();
        int x, y;
        int s = 0;
        int[] rect;
        for (int i = 0; i < rects.length; ++i) {
            rect = rects[i];
            x = Math.abs(rect[2] - rect[0]);
            y = Math.abs(rect[3] - rect[1]);
            s += (x + 1) * (y + 1);
            sum[i] = s;
        }
    }
    
    public int[] pick() {
        int id = random.nextInt(sum[sum.length - 1]) + 1;
        int l = 0, r = sum.length - 1, mid;
        while (l < r) {
            mid = (l + r) / 2;
            if (sum[mid] >= id) {
                r = mid;
            }
            else {
                l = mid + 1;
            }
        }
        if (l > 0) id -= sum[l - 1];
        id--;
        int[] rect = rects[l];
        int y = Math.abs(rect[3] - rect[1]);
        return new int[]{Math.min(rect[0], rect[2]) + id / (y + 1),
                        Math.min(rect[1], rect[3]) + id % (y + 1)};
        
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(rects);
 * int[] param_1 = obj.pick();
 */
