class Solution {
    public int minSteps(int n) {
        if (n == 1) return 0;
        int q = 2;
        
        int steps = 0;
        while (q * q <= n) {
            while (n % q == 0) {
                n /= q;
                steps += q;
            }
            q++;
        }
        if (n > 1) steps += n;
        
        return steps;
    }
}
