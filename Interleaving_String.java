class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        if (s1.equals("")) return s2.equals(s3);
        if (s2.equals("")) return s1.equals(s3);
        int length1 = s1.length(), length2 = s2.length();
        boolean[][] table = new boolean[length1 + 1][length2 + 1];
        table[0][0] = true;
        for (int j = 1; j <= length2; ++j) {
            table[0][j] = table[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }
        for (int i = 1; i <= length1; ++i) {
            table[i][0] = table[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
            for (int j = 1; j <= length2; ++j) {
                table[i][j] = (table[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
                        || (table[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }
        return table[length1][length2];
    }
}
