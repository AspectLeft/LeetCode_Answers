class Solution {
    public String licenseKeyFormatting(String S, int K) {
        char[] a = S.toUpperCase().toCharArray();
        int length = 0;
        for (char c: a) {
            if (isAlphanumeric(c)) {
                length++;
            }
        }
        if (length == 0) return "";
        int remainder = length % K;
        if (remainder == 0) remainder = K;
        StringBuilder builder = new StringBuilder();
        int i = 0;
        while (i < a.length) {
            while (i < a.length && !isAlphanumeric(a[i])) i++;
            if (i == a.length) break;
            builder.append(a[i]);
            remainder--;
            if (remainder == 0) {
                remainder = K;
                builder.append('-');
            }
            i++;
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
    
    private boolean isAlphanumeric(char c) {
        return ('0' <= c && c <= '9') || ('A' <= c && c <= 'Z');
    }
}
