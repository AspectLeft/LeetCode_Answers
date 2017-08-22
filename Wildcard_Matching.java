class Solution {
    public boolean isMatch(String s, String p) {
        
        int sLength = s.length();
        int pLength = p.length();
        boolean[][] table = new boolean[pLength + 1][sLength + 1];
        //for (boolean[] row: table) Arrays.fill(row, false);
        Arrays.fill(table[0], false);
        table[0][0] = true;
        for (int i = 1; i <= pLength; ++i) {
            for (int j = 0; j <= sLength; ++j) {
                table[i][j] = false;
                if (j > 0 && table[i - 1][j - 1] &&
                        (p.charAt(i - 1) == s.charAt(j - 1) || p.charAt(i - 1) == '*' || p.charAt(i - 1) == '?'))
                    table[i][j] = true;
                if (j > 0 && table[i][j - 1] &&
                        p.charAt(i - 1) == '*')
                    table[i][j] = true;
                if (table[i - 1][j] && p.charAt(i - 1) == '*')
                    table[i][j] = true;
            }
        }


        return table[pLength][sLength];
    }
}
