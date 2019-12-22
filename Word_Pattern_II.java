class Solution {
    public boolean wordPatternMatch(String pattern, String str) {
        if (pattern == null || str == null) return false;
        return match(new HashMap<>(), new HashMap<>(), pattern, 0, str, 0);
    }
    
    private boolean match(Map<Character, String> map1, 
                          Map<String, Character> map2,
                          String pattern, int i, String str, int j) {
        if (i == pattern.length() && j == str.length()) return true;
        if (i == pattern.length() || j == str.length()) return false;
        char c = pattern.charAt(i);
        String p = map1.get(c);
        if (p != null) {
            if (!str.substring(j).startsWith(p)) return false;
            return match(map1, map2, pattern, i + 1, str, j + p.length());
        }
        else {
            for (int j2 = j + 1; j2 <= str.length(); ++j2) {
                p = str.substring(j, j2);
                if (map2.get(p) != null) continue;
                
                map1.put(c, p);
                map2.put(p, c);
                if (match(map1, map2, pattern, i + 1, str, j2)) return true;
                map2.remove(p);
                map1.remove(c);
            }
            return false;
        }
    }
}
