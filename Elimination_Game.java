class Solution {
    public int lastRemaining(int n) {
        if (n == 1) return 1;
        if (n < 4) return 2;
        switch (n % 4) {
            case 0:
            case 1:
                return (lastRemaining(n >> 2) << 2) - 2;
            default:
                return lastRemaining(n >> 2) << 2;
        }
    }
}
