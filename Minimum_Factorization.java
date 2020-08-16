class Solution {
    private static int[] factors = {9, 8, 7, 6, 5, 4, 3, 2};
    
    public int smallestFactorization(int a) {
        if (a <= 2) return a;
        int[] powers = new int[10];
        for (int factor: factors) {
            while (a % factor == 0) {
                powers[factor]++;
                a /= factor;
            }
        }
        if (a != 1) return 0;
        StringBuilder builder = new StringBuilder();
        for (int factor: factors) {
            for (int i = 0; i < powers[factor]; ++i) {
                builder.insert(0, factor);
            }
        }
        if (Long.parseLong(builder.toString()) > Integer.MAX_VALUE) return 0;
        return Integer.parseInt(builder.toString());
    }
}
