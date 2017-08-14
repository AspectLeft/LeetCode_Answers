public class Solution {
    class ParenthesisString {
        int delta = 0;
        int left = 0;
        String string;
        ParenthesisString(String s) {
            string = s;
        }

        public String toString() {
            return string;
        }
    }

    public List<String> generateParenthesis(int n) {
        Stack<ParenthesisString> stack = new Stack<>();
        stack.push(new ParenthesisString(""));
        List<String> results = new ArrayList<>();
        ParenthesisString s;
        while (!stack.empty()) {
            s = stack.pop();
            if (s.delta > 0) {
                ParenthesisString s1 = new ParenthesisString(s.string + ")");
                s1.delta = s.delta - 1;
                s1.left = s.left;
                if (s1.string.length() == 2 * n) {
                    results.add(s1.string);
                }
                else {
                    stack.push(s1);
                }
            }
            if (s.delta < n && s.left < n) {
                ParenthesisString s2 = new ParenthesisString(s.string + "(");
                s2.delta = s.delta + 1;
                s2.left = s.left + 1;
                stack.push(s2);
            }
        }
        Set<String> set = new HashSet<>();
        set.addAll(results);
        results.clear();
        results.addAll(set);
        return results;
    }
}
