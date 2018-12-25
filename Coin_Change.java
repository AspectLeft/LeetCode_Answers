class Solution {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (coins == null || coins.length == 0) return -1;
        int n = coins.length;
        int[] prevlevel = new int[amount + 1];
        int[] level = new int[amount + 1];
        Arrays.fill(prevlevel, -1);
        prevlevel[0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= amount; ++j) {
                level[j] = prevlevel[j];
                if (j >= coins[i - 1] && level[j - coins[i - 1]] >= 0) {
                    if (level[j] == -1)
                        level[j] = level[j - coins[i - 1]] + 1;
                    else
                        level[j] = Math.min(level[j], level[j - coins[i - 1]] + 1);
                }
            }
            int[] tmp = prevlevel;
            prevlevel = level;
            level = tmp;
        }
        return prevlevel[amount];
    }
}
