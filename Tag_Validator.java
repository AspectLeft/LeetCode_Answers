class Solution {
    private boolean isUppercase(String tag) {
        char c;
        for (int i = 0; i < tag.length(); ++i) {
            c = tag.charAt(i);
            if (c < 'A' || c > 'Z') return false;
        }
        return true;
    }
    
    public boolean isValid(String code) {
        if (code == null || code.length() == 0) return false;
        Stack<String> stack = new Stack<>();
        boolean hasTag = false;
        if (code.charAt(0) != '<') return false;
        while (!code.isEmpty()) {
            int start = code.indexOf('<');
            if (start == -1) break;
            code = code.substring(start);
            if (code.length() < 2) return false;
            if (code.charAt(1) == '!') {
                if (stack.empty()) return false;
                if (!code.startsWith("<![CDATA[")) return false;
                int end = code.indexOf("]]>");
                if (end == -1) return false;
                code = code.substring(end + 3);
            }
            else if (code.charAt(1) == '/'){
                int end = code.indexOf('>');
                if (end == -1) return false;
                String tag = code.substring(2, end);
                if (stack.empty() || !stack.peek().equals(tag)) return false;
                stack.pop();
                code = code.substring(end + 1);
                if (!code.isEmpty() && stack.empty()) return false;
            }
            else {
                int end = code.indexOf('>');
                if (end == -1) return false;
                String tag = code.substring(1, end);
                if (tag.length() < 1 || tag.length() > 9 || !isUppercase(tag)) return false;
                stack.push(tag);
                hasTag = true;
                code = code.substring(end + 1);
            }
        }
        return stack.empty() && hasTag;
    }
}
