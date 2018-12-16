class Solution {
    class EvalException extends Exception {
        EvalException() {
            super();
        }
    }
    
    private int sign(int a) {
        if (a > 0) return 1;
        if (a == 0) return 0;
        return -1;
    }
    
    public void op(Stack<Integer> operands, char operator) throws EvalException {
        int a, b, c;
        b = operands.pop();
        a = operands.pop();
        switch(operator) {
            case '+':
                c = a + b;
                if ((a > 0 && b > 0 && c < 0) || (a < 0 && b < 0 && c >= 0))
                    throw new EvalException();
                operands.push(c);
                break;
            case '-':
                c = a - b;
                if ((c > 0 && b > 0 && a < 0) || (c < 0 && b < 0 && a >= 0))
                    throw new EvalException();
                operands.push(c);
                break;
            case '*':
                c = a * b;
                if (sign(a) * sign(b) != sign(c))
                    throw new EvalException();
                operands.push(c);
                break;
            default:
        }
    }
    
    private int parse(char[] arr) throws EvalException {
        int operand = arr[index] - '0';
        index++;
        while (index < arr.length && arr[index] >= '0' && arr[index] <= '9') {
            if (operand == 0) 
                throw new EvalException();
            operand = operand * 10 + (arr[index] - '0');
            if (operand < 0)
                throw new EvalException();
            index++;
        }
        return operand;
    }
    
    int index;
    
    private int calculate(String s) throws EvalException {
        s = s.replace(" ", "");
        char[] arr = s.toCharArray();
        Stack<Integer> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();
        index = 0;
        while (index < arr.length) {
            if (arr[index] >= '0' && arr[index] <= '9')
                operands.push(parse(arr));
            else switch (arr[index]) {
                case '+':
                case '-':
                    while (!operators.empty()) {
                        op(operands, operators.pop());
                    }
                    operators.push(arr[index]);
                    index++;
                    break;
                case '*':
                case '/':
                    while (!operators.empty() && (operators.peek() == '*')) {
                        op(operands, operators.pop());
                    }
                    operators.push(arr[index]);
                    index++;
                    break;
                default:
            }
        }
        while (!operators.empty()) op(operands, operators.pop());
        return operands.pop();
    }
    
    
    char[] arr;
    int n;
    
    
    List<String> expressions = new ArrayList<>();
    StringBuilder builder = new StringBuilder();
    
    private boolean isDigit(char c)  {
        return c >= '0' && c <= '9';
    }
    
    private void allExpressions(int start) {
        if (start == n - 1) {
            builder.append(arr[start]);
            expressions.add(builder.toString());
            builder.deleteCharAt(builder.length() - 1);
            return;
        }
        
        if (builder.length() > 0) {
            if (builder.charAt(builder.length() - 1) == '0') {
                if (builder.length() == 1 || !isDigit(builder.charAt(builder.length() - 2)))
                    return;
            }
        }
        
        builder.append(arr[start]);
        
        builder.append('*');
        
        allExpressions(start + 1);
        
        builder.setCharAt(builder.length() - 1, '+');
        
        allExpressions(start + 1);
        
        builder.setCharAt(builder.length() - 1, '-');
        
        allExpressions(start + 1);
        
        builder.deleteCharAt(builder.length() - 1);
        
        allExpressions(start + 1);
        
        builder.deleteCharAt(builder.length() - 1);
        
    }
      
    
    public List<String> addOperators(String num, int target) {
        arr = num.toCharArray();
        n = arr.length;
        List<String> output = new ArrayList<>();
        if (n == 0) return output;
        allExpressions(0);
        int v;
        for (String exp: expressions) {
            try {
                v = calculate(exp);
                if (v == target)
                    output.add(exp);
            }
            catch (EvalException e) {
                
            }
        }
        return output;
    }
}
