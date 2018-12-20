class Solution {
    private int length;
    
    private boolean isAdditiveNumber(
        long a, 
        long b, 
        String tail) {
        if (tail.length() == 0) return true;
        long c = a + b;
        String head = String.valueOf(c);
        if (head.length() <= tail.length() && tail.startsWith(head)) {
            return isAdditiveNumber(b, c, tail.substring(head.length()));
        }
        return false;
    }
    
    public boolean isAdditiveNumber(String num) {
        if (num == null || num.length() < 3) return false;
        length = num.length();
        int offset_a, offset_b;
        for (offset_a = 1; offset_a * 2 <= length; ++offset_a) {
            for (offset_b = offset_a + 1; (offset_b - offset_a) * 2 <= length && offset_b < length; ++offset_b) {
                if (!((offset_a > 1 && num.charAt(0) == '0') 
                      || (offset_b - offset_a > 1 && num.charAt(offset_a) == '0'))
                    && isAdditiveNumber(Long.parseLong(num.substring(0, offset_a)),
                                    Long.parseLong(num.substring(offset_a, offset_b)),
                                    num.substring(offset_b)))
                    return true;
            }
        }
        return false;
    }
    /*
    
    private boolean isAdditiveNumber(
        java.math.BigInteger a, 
        java.math.BigInteger b, 
        String tail) {
        if (tail.length() == 0) return true;
        java.math.BigInteger c = a.add(b);
        String head = c.toString();
        if (head.length() <= tail.length() && tail.startsWith(head)) {
            return isAdditiveNumber(b, c, tail.substring(head.length()));
        }
        return false;
    }
    
    public boolean isAdditiveNumber(String num) {
        if (num == null || num.length() < 3) return false;
        length = num.length();
        int offset_a, offset_b;
        for (offset_a = 1; offset_a * 2 <= length; ++offset_a) {
            for (offset_b = offset_a + 1; (offset_b - offset_a) * 2 <= length && offset_b < length; ++offset_b) {
                if (!((offset_a > 1 && num.charAt(0) == '0') 
                      || (offset_b - offset_a > 1 && num.charAt(offset_a) == '0'))
                    && isAdditiveNumber(new java.math.BigInteger(num.substring(0, offset_a)),
                                    new java.math.BigInteger(num.substring(offset_a, offset_b)),
                                    num.substring(offset_b)))
                    return true;
            }
        }
        return false;
    }
    */
}
