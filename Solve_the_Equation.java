class Solution {
    // ax+b
    class Linear {
        int a, b;
        Linear(int a, int b) {
            this.a = a;
            this.b = b;
        }
        
        @Override
        public String toString() {
            return String.format("%dx+%d", a, b);
        }
    }
    
    private boolean isDigit(char c) {
        return '0' <= c && c <= '9';
    }
    
    private Linear parseExpr(String expr) {
        int i = 0;
        char c;
        int a = 0, b = 0;
        int sign = 1;
        while (i < expr.length()) {
            c = expr.charAt(i);
            if (isDigit(c)) {
                int v = c - '0';
                i++;
                
                while (i < expr.length() && isDigit(expr.charAt(i))) {
                    c = expr.charAt(i);
                    v = v * 10 + (c - '0');
                    i++;
                }
                if (i < expr.length() && expr.charAt(i) == 'x') {
                    a += sign * v;
                    i++;
                }
                else {
                    b += sign * v;
                }
            }
            else if (c == '+' || c == '-') {
                sign = c == '+' ? 1 : -1;
                i++;
            }
            else {
                a += sign;
                i++;
            }
        }
        
        return new Linear(a, b);
    }
    
    public String solveEquation(String equation) {
        if (equation == null) return null;
        equation = equation.replace(" ", "");
        if (equation.isEmpty()) return null;
        
        int equalIndex = equation.indexOf('=');
        
        String lhs = equation.substring(0, equalIndex);
        String rhs = equation.substring(equalIndex + 1);
        
        Linear l = parseExpr(lhs), r = parseExpr(rhs);
        // System.out.println(l);
        // System.out.println(r);
        
        if (l.a == r.a) {
            return (l.b == r.b) ? "Infinite solutions" : "No solution";
        }
        
        return String.format("x=%d", (r.b - l.b) / (l.a - r.a));
    }
}
