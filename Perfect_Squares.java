class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        List<Integer> squares = new ArrayList<>();
        for (int j = 0, square = 0; square <= n; square = j * j, squares.add(square), ++j);
        int last = squares.get(squares.size() - 2);
        if (last == n) return 1;
        for (int i = 2; i <= n; ++i) {
            dp[i] = i;
            for (int j = 1, square = 1; square <= i; ++j, square = squares.get(j)) {
                dp[i] = Math.min(dp[i], dp[i - square] + 1);
            }
        }
        return dp[n];
    }
}
