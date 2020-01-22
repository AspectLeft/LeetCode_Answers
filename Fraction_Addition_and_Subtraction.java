class Solution {
    class Rational {
        int p, q;
        int sign;
        
        Rational(int p, int q, int sign) {
            this.p = p;
            this.q = q;
            this.sign = sign;
            if (p == 0) sign = 0;
        }
        
        @Override
        public String toString() {
            if (sign == 0) return "0/1";
            String s = String.format("%d/%d", p, q);
            if (sign < 0) s = "-" + s;
            return s;
        }
        
        private int getSign(int v) {
            if (v > 0) return 1;
            if (v < 0) return -1;
            return 0;
        }
        
        private int gcd(int a, int b) {
            if (a == 0) return b;
            return gcd(b % a, a);
        }
        
        public void add(Rational b) {
            if (b.sign == 0) return;
            if (sign == 0) {
                this.p = b.p;
                this.q = b.q;
                this.sign = b.sign;
                return;
            }
            int p3, q3, g;
            if (this.sign == b.sign) {
                p3 = p * b.q + q * b.p;
                q3 = q * b.q;
                g = gcd(p3, q3);
                p3 /= g;
                q3 /= g;
                this.p = p3;
                this.q = q3;
                return;
            }
            p3 = p * b.q - q * b.p;
            q3 = q * b.q;
            g = gcd(Math.abs(p3), q3);
            p3 /= g;
            q3 /= g;
            sign *= getSign(p3);
            p3 = Math.abs(p3);
            p = p3;
            q = q3;
        }
    }
    
    private boolean isDigit(char c) {
        return '0' <= c && c <= '9';
    }
    
    public String fractionAddition(String exp) {
        Rational result = new Rational(0, 1, 0);
        int p, q, sign;
        int i = 0;
        while (i < exp.length()) {
            if (exp.charAt(i) == '-') {
                sign = -1;
                i++;
            }
            else if (exp.charAt(i) == '+') {
                sign = 1;
                i++;
            }
            else {
                sign = 1;
            }
            
            p = 0;
            while (isDigit(exp.charAt(i))) {
                p *= 10;
                p += exp.charAt(i) - '0';
                i++;
            }
            i++;
            q = 0;
            while (i < exp.length() && isDigit(exp.charAt(i))) {
                q *= 10;
                q += exp.charAt(i) - '0';
                i++;
            }
            result.add(new Rational(p, q, sign));
        }
        
        return result.toString();
    }
}
