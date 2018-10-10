class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        int opt1[] = new int[prices.length], opt2[] = new int[prices.length];
        opt1[0] = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; ++i) {
            opt1[i] = Math.max(opt1[i - 1], prices[i] - min);
            if (prices[i] < min) min = prices[i];
        }
        opt2[prices.length - 1] = 0;
        int max = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; --i) {
            opt2[i] = Math.max(opt2[i + 1], max - prices[i]);
            if (prices[i] > max) max = prices[i];
        }
        max = 0;
        for (int i = 0; i < prices.length; ++i) {
            max = Math.max(max, opt1[i] + opt2[i]);
        }
        return max;
    }
}
