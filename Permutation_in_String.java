class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s1.length() == 0) return true;
        if (s1.length() > s2.length()) return false;
        int[] table = new int[26];
        for (int i = 0; i < s1.length(); ++i) {
            table[s1.charAt(i) - 'a']--;
            table[s2.charAt(i) - 'a']++;
        }
        int delta = 0;
        for (int k = 0; k < 26; ++k) {
            delta += Math.abs(table[k]);
        }
        if (delta == 0) return true;
        for (int i = s1.length(), k1, k2; i < s2.length(); ++i) {
            k1 = s2.charAt(i - s1.length()) - 'a';
            k2 = s2.charAt(i) - 'a';
            
            table[k1]--;
            if (table[k1] >= 0) delta--;
            else delta++;
            
            table[k2]++;
            if (table[k2] <= 0) delta--;
            else delta++;
            
            if (delta == 0) return true;
        }
        return false;
    }
}
