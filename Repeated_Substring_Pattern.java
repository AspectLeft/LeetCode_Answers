class Solution {
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        double sqrt = Math.sqrt(n);
        for (int subLength = 1; subLength <= n / 2; ++subLength) {
            if ((s.length() % subLength) != 0) continue;
            if (match(s, s.substring(0, subLength))) {
                return true;
            }
        }
        return false;
    }
    
    private boolean match(String s, String sub) {
        int stride = sub.length();
        for (int start = 0; start < s.length(); start += stride) {
            if (!sub.equals(s.substring(start, start + stride))) {
                return false;
            }
        }
        return true;
    }
}
