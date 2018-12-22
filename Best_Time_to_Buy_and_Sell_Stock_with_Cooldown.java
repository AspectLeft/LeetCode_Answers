class Solution {
    public int maxProfit(int[] prices) {
        
        if (prices == null || prices.length < 2) return 0;
        int n = prices.length;
        
        int opt11, opt12, opt13;
        int opt21, opt22;
        
        opt13 = 0;
        opt12 = - prices[n - 2] + prices[n - 1];
        if (opt12 < 0) opt12 = 0;
        opt22 = 0;
        
        
        int output = Math.max(opt12, opt13);
        
        for (int k = n - 3; k >= 0; --k) {
            opt11 = - prices[k] + prices[k + 1] + opt12;
            if (opt13 > opt11) opt11 = opt13;
            if (opt22 > opt11) opt11 = opt22;
            opt21 = opt22 > opt12 ? opt22 : opt12;
            if (opt11 > output) output = opt11;
            
            opt13 = opt12;
            opt12 = opt11;
            
            opt22 = opt21;
            
        }
        
        /*
        if (prices == null || prices.length < 2) return 0;
        int n = prices.length;
        int[] opt = new int[n + 2]; // opt[k]: max profit from day k to n - 1, and must buy on k;
        int[] opt2 = new int[n + 2]; // opt[k]: max profit from day k to n - 1, and must not buy on k
        opt[n - 1] = 0;
        opt[n - 2] = - prices[n - 2] + prices[n - 1];
        opt2[n - 1] = 0;
        opt2[n - 2] = 0;
        if (opt[n - 2] < 0) opt[n - 2] = 0;
        int output = Math.max(opt[n - 1], opt[n - 2]);
        for (int k = n - 3; k >= 0; --k) {
            opt[k] = Math.max(opt[k + 2], - prices[k] + prices[k + 1] + opt[k + 1]);
            opt[k] = Math.max(opt[k], opt2[k + 1]);
            opt2[k] = Math.max(opt2[k + 1], opt[k + 1]);
            if (opt[k] > output) output = opt[k];
        }
        
        */
        return output;
    }
}
