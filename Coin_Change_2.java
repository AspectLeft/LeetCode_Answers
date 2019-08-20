class Solution {
    int[][] memoi;
    
    public int change(int amount, int[] coins) {
        if (amount == 0) return 1;
        if (coins == null || coins.length == 0) return 0;
        memoi = new int[amount + 1][coins.length];
        Arrays.fill(memoi[0], 1);
        for (int a = 1; a <= amount; ++a) {
            for (int k = 0; k < coins.length; ++k) {
                memoi[a][k] = (k > 0 ? memoi[a][k - 1] : 0)
                    + (a >= coins[k] ? memoi[a - coins[k]][k] : 0);
            }
        }
        return memoi[amount][coins.length - 1];
    }
}
