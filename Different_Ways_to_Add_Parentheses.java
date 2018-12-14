class Solution {
    private List<Integer> operands = new ArrayList<>();
    private List<Character> operators = new ArrayList<>();
    
    
    private void parse(String input) {
        char[] arr = input.toCharArray();
        int i = 0;
        while (i < arr.length) {
            if (arr[i] >= '0' && arr[i] <= '9') {
                int v = arr[i] - '0';
                i++;
                while (i < arr.length && arr[i] >= '0' && arr[i] <= '9') {
                    v = v * 10 + (arr[i] - '0');
                    i++;
                }
                operands.add(v);
            }
            else {
                operators.add(arr[i]);
                i++;
            }
        }
    }
    
    private int op(int a, int b, char operator) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
        }
        return a;
    }
    
    private List<Integer> divide(int l, int r) {
        List<Integer> output = new ArrayList<>();
        if (l == r) {
            output.add(op(operands.get(l), operands.get(l + 1), operators.get(l)));
            return output;
        }
        int v = operands.get(l);
        char o = operators.get(l);
        for (int b: divide(l + 1, r)) {
            output.add(op(v, b, o));
        }
        List<Integer> o1, o2;
        
        for (int lastOp = l + 1; lastOp < r; ++lastOp) {
            o1 = divide(l, lastOp - 1);
            o2 = divide(lastOp + 1, r);
            o = operators.get(lastOp);
            for (int v1: o1)
                for (int v2: o2)
                    output.add(op(v1, v2, o));
        }
        
        v = operands.get(r + 1);
        o = operators.get(r);
        for (int a: divide(l, r - 1))
            output.add(op(a, v, o));
        
        return output;
    }
    
    
    public List<Integer> diffWaysToCompute(String input) {
        if (input == null || input == "") return new ArrayList<>();
        parse(input);
        if (operators.size() == 0) return Collections.singletonList(operands.get(0));
        return divide(0, operators.size() - 1);
    }
}
