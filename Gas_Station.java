class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || cost == null || gas.length == 0 || cost.length == 0) return -1;
        int n = gas.length;
        int[] profit = new int[n];
        for (int i = 0; i < n; ++i)
            profit[i] = gas[i] - cost[i];
        int[] subsum = new int[n];
        subsum[0] = profit[0];
        int min = profit[0];
        for (int i = 1; i < n; ++i) {
            subsum[i] = subsum[i - 1] + profit[i];
            min = Math.min(min, subsum[i - 1]);
        }
        
        if (subsum[n - 1] < 0) return -1;
        if (min >= 0) return 0;
        
        for (int j = 1; j < n; ++j) {
            min -= profit[j - 1];
            if (min >= 0) return j;
        }
        return -1;
    }
}
