class Solution {
    private int parse(char[] arr) {
        int operand = arr[index] - '0';
        index++;
        while (index < arr.length && arr[index] >= '0' && arr[index] <= '9') {
            operand = operand * 10 + (arr[index] - '0');
            index++;
        }
        return operand;
    }
    
    private int index = 0;
    private Stack<Character> operators = new Stack<>();
    private Stack<Integer> operands = new Stack<>();
    
    private void op(char operator) {
        int a, b;
        
        b = operands.pop();
        a = operands.pop();
        switch (operator) {
            case '+':
                operands.push(a + b);
                break;
            case '-':
                operands.push(a - b);
                break;
            default:
        };
        
    }
    
    public int calculate(String s) {
        char[] arr = s.toCharArray();
        while (index < arr.length) {
            if (arr[index] == ' ') { index++; continue; }
            if (arr[index] >= '0' && arr[index] <= '9')
                operands.push(parse(arr));
            else if (arr[index] == ')') {
                while (operators.peek() != '(') {
                    op(operators.pop());
                }
                operators.pop();
                index++;
            }
            else if (arr[index] == '+' || arr[index] == '-') {
                while (!operators.empty() && operators.peek() != '(') {
                    op(operators.pop());
                }
                operators.push(arr[index]);
                index++;
            }
            else {
                operators.push(arr[index]);
                index++;
            }
        }
        while (!operators.empty()) op(operators.pop());
        return operands.pop();
    }
}
