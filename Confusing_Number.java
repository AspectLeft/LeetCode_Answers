class Solution {
    private boolean valid(char c) {
        switch (c) {
            case '0':
            case '1':
            case '9':
            case '8':
            case '6':
                return true;
        }
        return false;
    }
    
    private char counterpart(char c) {
        if (c == '6') return '9';
        if (c == '9') return '6';
        return c;
    }
    
    
    public boolean confusingNumber(int N) {
        if (N == 0) return false;
        String s = String.valueOf(N);
        for (int i = 0; i < s.length(); ++i) {
            if (!valid(s.charAt(i))) return false;
        }
        return N != Integer.valueOf(rotate(s));
    }
    
    private String rotate(String s) {
        StringBuilder builder = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; --i) {
            builder.append(counterpart(s.charAt(i)));
        }
        return builder.toString();
    }
}
