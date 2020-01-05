class Solution {
    private boolean isValue(char c) {
        return c == 'T' || c == 'F' || ('0' <= c && c <= '9');
    }
    
    public String parseTernary(String exp) {
        if (exp == null || exp.length() < 2) return exp;
        Stack<String> operands = new Stack<>();
        for (int i = exp.length() - 1; i >= 0; --i) {
            if (isValue(exp.charAt(i))) {
                operands.push(exp.substring(i, i + 1));
                continue;
            }
            if (exp.charAt(i) == '?') {
                i--;
                String o1 = operands.pop();
                String o2 = operands.pop();
                operands.push(exp.charAt(i) == 'T' ? o1 : o2);
            }
            
        }
        return operands.pop();
    }
}
