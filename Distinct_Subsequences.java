class Solution {
    public int numDistinct(String s, String t) {
        if (s == null || t == null || t.length() == 0 || t.length() > s.length()) return 0;
        int sl = s.length(), tl = t.length();
        int count[][] = new int[sl + 1][tl + 1];
        Arrays.fill(count[0], 0);
        for (int i = 0; i < sl + 1; ++i) count[i][0] = 1;
        for (int i = 1; i < sl + 1; ++i) {
            for (int j = Math.max(1, i - (sl - tl)); j < tl + 1; ++j) {
                count[i][j] = count[i - 1][j];
                if (s.charAt(i - 1) == t.charAt(j - 1))
                    count[i][j] += count[i - 1][j - 1];
            }
        }
        return count[sl][tl];
    }
}
