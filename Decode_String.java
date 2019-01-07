class Solution {
    class Unit{
        int k;
        StringBuilder builder;
        Unit(int r) {
            k = r;
            builder = new StringBuilder();
        }
    }
    
    private char[] arr;
    private Stack<Unit> stack;
    int index;
    
    private int parseInt() {
        int v = 0;
        while (arr[index] >= '0' && arr[index] <= '9') {
            v = v * 10 + (arr[index] - '0');
            index++;
        }
        return v;
    }
    
    public String decodeString(String s) {
        if (s == null || s.length() == 0) return "";
        arr = s.toCharArray();
        stack = new Stack<>();
        index = 0;
        stack.push(new Unit(1));
        while (index < arr.length) {
            if (arr[index] >= '0' && arr[index] <= '9') {
                stack.push(new Unit(parseInt()));
                index++;
            }
            else if (arr[index] != ']') {
                stack.peek().builder.append(arr[index]);
                index++;
            }
            else { // [
                Unit unit = stack.pop();
                String u = unit.builder.toString();
                Unit parent = stack.peek();
                for (int i = 0; i < unit.k; ++i)
                    parent.builder.append(u);
                index++;
            }
        }
        return stack.pop().builder.toString();
    }
}
