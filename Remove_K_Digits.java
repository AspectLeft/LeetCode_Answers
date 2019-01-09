class Solution {
    public String removeKdigits(String num, int k) {
        if (k == num.length()) return "0";
        char[] a = num.toCharArray();
        int index = 0;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < k; ++i) {
            while (index < a.length && (stack.empty() || a[index] >= stack.peek())) {
                stack.push(a[index]);
                index++;
            }
            stack.pop();
        }
        while (index < a.length) stack.push(a[index++]);
        StringBuilder builder = new StringBuilder();
        while (!stack.empty()) builder.insert(0, stack.pop());
        int offset = 0;
        while (offset < builder.length() && builder.charAt(offset) == '0') 
            offset++;
        if (offset == builder.length()) return "0";
        return builder.substring(offset);
    }
}
