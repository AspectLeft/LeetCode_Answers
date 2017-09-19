class Solution {
    public int numDecodings(String s) {
        int length = s.length();
        if (length == 0) return 0;
        int[] table = new int[length + 1];
        table[0] = 1;
        char c;
        c = s.charAt(0);
        if (c >= '1' && c <= '9') {
            table[1] = 1;
        }
        else {
            return 0;
        }
        for (int i = 1; i < length; ++i) {
            table[i + 1] = 0;
            c = s.charAt(i);
            char prev = s.charAt(i - 1);
            if (c >= '1' && c <= '9') {
                table[i + 1] += table[i];
            }
            int code = (prev - '0') * 10 + (c - '0');
            if (code >= 10 && code <= 26) {
                table[i + 1] += table[i - 1];
            }
        }

        return table[length];
    }

}
