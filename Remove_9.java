class Solution {
    private int pow10(int p) {
        int b = 1;
        for (int i = 0; i < p; ++i) {
            b *= 10;
        }
        return b;
    }
    
    public int newInteger(int n) {
        return helper(n);
    }
    
    private int helper(int n) {
        if (n < 9) return n;
        long b = 1, p = 0;
        while (b * 9 <= n) {
            b *= 9;
            p++;
        }
        int msb = (int) (n / b);
        // System.out.println(n % b);
        return pow10((int)p) * msb + helper((int) (n % b));
    }
}
