class Solution {
    private boolean isOneInsertDistance(String s, String t) {
        int start = 0;
        while (start < s.length() && s.charAt(start) == t.charAt(start)) start++;
        if (start == s.length()) return true;
        while (start < s.length() && s.charAt(start) == t.charAt(start + 1)) start++;
        return start == s.length();
    }
    
    private boolean isOneReplaceDistance(String s, String t) {
        int i = 0;
        while (i < s.length() && s.charAt(i) == t.charAt(i)) i++;
        if (i == s.length()) return false;
        i++;
        while (i < s.length() && s.charAt(i) == t.charAt(i)) i++;
        return i == s.length();
    }
    
    private boolean isOneDeleteDistance(String s, String t) {
        
        return isOneInsertDistance(t, s);
    }
    
    public boolean isOneEditDistance(String s, String t) {
        if (s == null || t == null) return false;
        switch (s.length() - t.length()) {
            case -1:
                return isOneInsertDistance(s, t);
            case 0:
                return isOneReplaceDistance(s, t);
            case 1:
                return isOneDeleteDistance(s, t);
            default:
                return false;
        }
    }
}
