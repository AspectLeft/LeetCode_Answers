class Solution {
    private static int[] small = new int[]{1, 2, 4, 6};
    
    private static int[] pow3 = new int[19];
    
    static {
        pow3[0] = 1;
        for (int i = 1; i < 19; ++i)
            pow3[i] = 3 * pow3[i - 1];
    }
    
    public int integerBreak(int n) {
        if (n < 6) return small[n - 2];
        
        int output = 0;
        switch (n % 3) {
            case 0:
                return pow3[n / 3];
            case 1:
                return 4 * pow3[(n - 4) / 3];
            case 2:
                return 2 * pow3[(n - 2) / 3];
        }
        return 0;
    }
    
}
