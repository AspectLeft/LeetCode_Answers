class Solution {
    public double myPow(double x, int n) {
        
        if (n == 0)
            return 1;
        if (n == 1)
            return x;
        if (n == Integer.MIN_VALUE) {
            double p = myPow(x, 1073741824);
            return 1 / (p * p);
        }
        if (n < 0)
            return 1 / myPow(x, -n);
        if (n % 2 == 0) {
            double p = myPow(x, n / 2);
            return p * p;
        }
        else
            return x * myPow(x, n - 1);
    }
}
