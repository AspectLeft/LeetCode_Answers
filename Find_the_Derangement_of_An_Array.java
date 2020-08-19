class Solution {
    private static final int MOD = 1000000007;
    
    // https://zh.wikipedia.org/wiki/错排问题
    // f(n) = (n - 1) (f(n-1)) + f(n-2))
    public int findDerangement(int n) {
        if (n < 1) return 0;
        if (n == 1) return 0;
        if (n == 2) return 1;
        
        long c = 0, b = 1, a = 0;
        
        for (int i = 3; i <= n; ++i) {
            c = ((i - 1) * ((b + a) % MOD)) % MOD;
            
            a = b;
            b = c;
        }
        
        return (int) c;
    }
}
