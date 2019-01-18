class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] a = s.toCharArray();
        boolean[] table = new boolean[256];
        int l = 0, r = 0;
        int max = 0;
        while (r < a.length) {
            if (table[a[r]]) {
                max = Math.max(max, r - l);
                while (a[l] != a[r]) {
                    table[a[l]] = false;
                    l++;
                }
                l++;
                r++;
            }
            else {
                table[a[r]] = true;
                r++;
            }
        }
        max = Math.max(max, r - l);
        return max;
    }
}
