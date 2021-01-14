class Solution {
    private static class Rational {
        int p, q;
        
        Rational(int v) {
            this.p = v;
            this.q = 1;
        }
        
        Rational(int a, int b) {
            int g = gcd(Math.abs(a), Math.abs(b));
            this.p = a / g;
            this.q = b / g;
        }
        
        private int gcd(int a, int b) {
            if (a * b == 0) return a + b;
            if (b > a) return gcd(b, a);
            return gcd(b, a % b);
        }
        
        Rational add(Rational b) {
            return new Rational(p * b.q + q * b.p, q * b.q);
        }
        
        Rational sub(Rational b) {
            return new Rational(p * b.q - q * b.p, q * b.q);
        }
        
        Rational mul(Rational b) {
            return new Rational(p * b.p, q * b.q);
        }
        
        Rational div(Rational b) {
            return new Rational(p * b.q, q * b.p);
        }
        
        public Rational op(Rational b, char operator) {
            switch (operator) {
                case '+':
                    return add(b);
                case '-':
                    return sub(b);
                case '*':
                    return mul(b);
                case '/':
                    return div(b);
            }
            return null;
        }
        
        @Override
        public boolean equals(Object o) {// self check
            if (this == o)
                return true;
            // null check
            if (o == null)
                return false;
            // type check and cast
            if (getClass() != o.getClass())
                return false;
            Rational b = (Rational) o;
            
            return (p == b.p && q == b.q) || (p == -b.p && q == -b.q);
        }
        
        @Override
        public String toString() {
            return String.valueOf(1.0 * p / q);
        }
    }
    
    public boolean judgePoint24(int[] nums) {
        List<Rational> list = new ArrayList<>();
        for (int v: nums) {
            list.add(new Rational(v));
        }
        
        // System.out.println((new Rational(6)).div(new Rational(8)));
        
        return dfs(list);
    }
    
    private static final char[] OPS = {'+', '-', '*', '/'};
    private static final Rational TARGET = new Rational(24);
    
    private boolean dfs(List<Rational> nums) {
        int n = nums.size();
        if (n == 1) {
            // System.out.println(nums.get(0));
            return nums.get(0).equals(TARGET);
        }
        
        for (int i = 0; i < n; ++i) {
            Rational a = nums.get(i);
            for (int j = 0; j < n; ++j) {
                if (j == i) continue;
                Rational b = nums.get(j);
                for (char op: OPS) {
                    if (op == '/' && b.p == 0) continue;
                    List<Rational> nextNums = new ArrayList<>();
                    nextNums.add(a.op(b, op));
                    for (int k = 0; k < n; ++k) {
                        if (k == i || k == j) continue;
                        nextNums.add(nums.get(k));
                    }
                    
                    if (dfs(nextNums)) return true;
                }
            }
        }
        
        return false;
    }
}
