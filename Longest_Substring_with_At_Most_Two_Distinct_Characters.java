class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] table = new int[256];
        int count = 0;
        int l = 0, r = 0;
        char[] a = s.toCharArray();
        int output = 0;
        while (r < a.length) {
            table[a[r]]++;
            if (table[a[r]] == 1) {
                count++;
            }
            if (count <= 2) {
                output = Math.max(output, r - l + 1);
            }
            else {
                while (count > 2) {
                    table[a[l]]--;
                    if (table[a[l]] == 0)
                        count--;
                    l++;
                }
            }
            r++;
        }
        return output;
    }
}
