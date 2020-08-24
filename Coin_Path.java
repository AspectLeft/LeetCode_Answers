class Solution {
    public List<Integer> cheapestJump(int[] A, int B) {
        int n = A.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        dp[n - 1] = A[n - 1];
        int[] next = new int[n];
        for (int i = n - 2; i >= 0; --i) {
            dp[i] = Integer.MAX_VALUE;
            next[i] = -1;
            if (A[i] == -1) {
                dp[i] = -1;
                continue;
            }
            for (int j = i + 1; j < Math.min(n, i + B + 1); ++j) {
                if (dp[j] < 0) continue;
                if (dp[j] < dp[i]) {
                    dp[i] = dp[j];
                    next[i] = j;
                }
            }
            if (next[i] >= 0) {
                dp[i] += A[i];
            }
            else {
                dp[i] = -1;
            }
        }
        LinkedList<Integer> path = new LinkedList<>();
        if (dp[0] < 0) return path;
        int k = 0;
        while (k != n - 1) {
            path.add(k + 1);
            k = next[k];
        }
        path.add(n);
        return path;
        
//         int n = A.length;
//         int[] dp = new int[n];
//         Arrays.fill(dp, -1);
//         dp[0] = A[0];
//         int[] prev = new int[n];
        
        
//         for (int i = 1; i < n; ++i) {
//             dp[i] = Integer.MAX_VALUE;
//             prev[i] = -1;
//             if (A[i] == -1) {
//                 dp[i] = -1;
//                 continue;
//             }
//             for (int j = i - 1; j >= Math.max(0, i - B); --j) {
//                 if (dp[j] < 0) continue;
//                 if (dp[j] < dp[i] || (dp[j] == dp[i] && A[j] == 0)) {
//                     dp[i] = dp[j];
//                     prev[i] = j;
//                 }
//             }
//             if (prev[i] >= 0) {
//                 dp[i] += A[i];
//             }
//             else {
//                 dp[i] = -1;
//             }
//         }
//         LinkedList<Integer> path = new LinkedList<>();
//         if (dp[n - 1] < 0) return path;
//         int k = n - 1;
//         while (k > 0) {
//             path.addFirst(k + 1);
//             k = prev[k];
//         }
//         path.addFirst(1);
        
//         return path;
    }
}
