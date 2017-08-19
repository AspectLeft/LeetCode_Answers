class Solution {
    public int longestValidParentheses(String s) {
        int result = 0;
        int length = s.length();
        char c;
        int start = 0;
        int newResult;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < length; ++i) {
            c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            }
            else {
                if (stack.isEmpty()) {
                    start = i + 1;
                }
                else {
                    stack.pop();
                    if (stack.isEmpty()) {
                        newResult = 1 + i - start;
                    }
                    else {
                        newResult = i - stack.peek();
                    }
                    if (newResult > result)
                        result = newResult;
                }

            }
        }

        return result;
    }
}
