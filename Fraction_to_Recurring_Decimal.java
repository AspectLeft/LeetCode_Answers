class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";
        StringBuilder builder = new StringBuilder();
        long n = numerator, d = denominator;
        boolean sign = (n > 0 && d > 0) || (n < 0 && d < 0);
        if (n < 0) n = - n;
        if (d < 0) d = - d;
        long integer = n / d;
        if (!sign) builder.append('-');
        builder.append(integer);
        n = n % d;
        if (n == 0) return builder.toString();
        builder.append('.');
        Map<Long, Integer> map = new HashMap<>();
        long bit;
        int start = builder.length();
        map.put(n, start);
        while (true) {
            n *= 10;
            bit = n / d;
            n = n % d;
            if (n == 0) {
                builder.append(bit);
                return builder.toString();
            }
            Integer index = map.get(n);
            if (index != null) {
                builder.append(bit);
                builder.insert(index, "(");
                builder.append(')');
                return builder.toString();
            }
            builder.append(bit);
            map.put(n, builder.length());
        }
        
    }
}
