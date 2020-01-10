class Solution {
    public boolean splitArray(int[] nums) {
        if (nums == null) return false;
        int n = nums.length;
        if (n < 7) return false;
        int[][] rangeSum = new int[n][n];
        for (int l = 0; l < n; ++l) {
            rangeSum[l][l] = nums[l];
            for (int r = l + 1; r < n; ++r) {
                rangeSum[l][r] = rangeSum[l][r - 1] + nums[r];
            }
        }
        
        Map<Integer, List<Integer>> rightSumMap = new HashMap<>();
        int s4 = 0;
        for (int k = n - 2; k >= 5; --k) {
            s4 += nums[k + 1];
            List<Integer> list = rightSumMap.get(s4);
            if (list == null) {
                list = new ArrayList<>();
                rightSumMap.put(s4, list);
            }
            list.add(k);
        }
        
        int s1 = 0;
        for (int i = 1; i <= n - 6; ++i) {
            s1 += nums[i - 1];
            List<Integer> kList = rightSumMap.get(s1);
            if (kList == null) continue;
            for (int k: kList) {
                if (i > k - 4) break;
                for (int j = i + 2; j <= k - 2; ++j) {
                    if (rangeSum[i + 1][j - 1] == s1
                       && rangeSum[j + 1][k - 1] == s1)
                        return true;
                }
            }
        }
        return false;
    }
}
