import java.math.BigInteger;
class Solution {
    public String nearestPalindromic(String n) {
        if (n.length() % 2 == 1) {
            StringBuilder builder;
            if (!isPalindrome(n)) {
                builder = new StringBuilder();
                builder.append(n.substring(0, n.length() / 2));
                builder.reverse();
                builder.insert(0, n.substring(0, n.length() / 2 + 1));
                BigInteger b = new BigInteger(n);
                BigInteger b1 = new BigInteger(builder.toString());
                int mid = n.length() / 2;
                char c = n.charAt(mid);
                if (c > '0') {
                    builder.setCharAt(mid, (char) (c - 1));
                    BigInteger b2 = new BigInteger(builder.toString());
                    b1 = closer(b1, b2, b);
                }
                if (c < '9') {
                    builder.setCharAt(mid, (char) (c + 1));
                    BigInteger b2 = new BigInteger(builder.toString());
                    b1 = closer(b1, b2, b);
                }
                return closerWith9(b1.toString(), n);
            }
            builder = new StringBuilder(n);
            int mid = n.length() / 2;
            char c = n.charAt(mid);
            if (c > '0') {
                builder.setCharAt(mid, (char) (c - 1));
            }
            else {
                builder.setCharAt(mid, '1');
            }
            if (n.length() == 1) return builder.toString();
            return closerWith9(builder.toString(), n);
        }
        BigInteger b = new BigInteger(n.substring(0, n.length() / 2));
        BigInteger b1 = b.add(BigInteger.ONE), b2 = b.subtract(BigInteger.ONE);
        String v1 = buildPalindrome(b1.toString());
        String v2 = buildPalindrome(b2.toString());
        b1 = new BigInteger(v1);
        b2 = new BigInteger(v2);
        b = new BigInteger(n);
        System.out.println(b1);
        System.out.println(b2);
        b1 = closer(b1, b2, b);

        if (!isPalindrome(n)) {
            BigInteger b3 = new BigInteger(buildPalindrome(n.substring(0, n.length() / 2)));
            b1 = closer(b1, b3, b);
        }
        return closerWith9(b1.toString(), n);
    }

    private boolean isPalindrome(String n) {
        for (int i = 0, j = n.length() - 1; i < j; ++i, --j) {
            if (n.charAt(i) != n.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    private BigInteger closer(BigInteger b1, BigInteger b2, BigInteger b) {
        int c = b1.subtract(b).abs().compareTo(b.subtract(b2).abs());
        if (c < 0) return b1;
        if (c > 0) return b2;
        c = b1.compareTo(b2);
        if (c < 0) return b1;
        return b2;
    }

    private String closerWith9(String v, String n) {
        BigInteger b1 = new BigInteger(v);
        BigInteger b = new BigInteger(n);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n.length() - 1; ++i) builder.append('9');
        BigInteger b3 = new BigInteger(builder.toString());

        builder = new StringBuilder();
        builder.append('1');
        for (int i = 0; i < n.length() - 1; ++i) builder.append('0');
        builder.append('1');
        BigInteger b4 = new BigInteger(builder.toString());
        if (b4.subtract(b).abs().compareTo(b.subtract(b3).abs()) < 0) {
            b3 = b4;
        }

        if (b1.subtract(b).abs().compareTo(b.subtract(b3).abs()) < 0) {
            return b1.toString();
        }
        else {
            return b3.toString();
        }
    }

    private String buildPalindrome(String half) {
        StringBuilder builder = new StringBuilder();
        builder.append(half).reverse().insert(0, half);
        return builder.toString();
    }
}
