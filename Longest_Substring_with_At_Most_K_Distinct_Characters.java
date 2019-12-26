class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k == 0) return 0;
        int[] table = new int[256];
        int count = 0;
        int i = 0, j = 0;
        int maxLength = 0;
        while (i < s.length()) {
            while (j < s.length() && count <= k) {
                table[s.charAt(j)]++;
                if (table[s.charAt(j)] == 1) count++;
                j++;
            }
            if (count > k) {
                maxLength = Math.max(maxLength, j - i - 1);
                //System.out.println(maxLength);
            }
            else {
                maxLength = Math.max(maxLength, j - i);
                //System.out.println(maxLength);
                break;
            }
            while (i < s.length() && count > k) {
                table[s.charAt(i)]--;
                if (table[s.charAt(i)] == 0) count--;
                i++;
            }
        }
        return maxLength;
    }
}
