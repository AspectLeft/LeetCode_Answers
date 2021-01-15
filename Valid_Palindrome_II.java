class Solution {
    public boolean validPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i < j; ++i, --j) {
            if (s.charAt(i) != s.charAt(j)) {
                return valid0(s.substring(i, j)) || valid0(s.substring(i + 1, j + 1));
            }
        }
        return true;
    }
    
    private boolean valid0(String s) {
        for (int i = 0, j = s.length() - 1; i < j; ++i, --j) {
            if (s.charAt(i) != s.charAt(j)) return false;
        }
        return true;
    }
}
