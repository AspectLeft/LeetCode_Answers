class Solution {
    
    public int minDistance(String word1, String word2) {
        if (word1.length() == 0 || word2.length() == 0)
            return word1.length() + word2.length();
        int length1 = word1.length(), length2 = word2.length();
        if (length1 > length2) {
            String tmp = word1;
            word1 = word2;
            word2  = tmp;
            length1 = word1.length();
            length2 = word2.length();
        }
        int[][] table = new int[length1 + 1][length2 + 1];
        for (int j = 0; j <= length2; ++j) {
            table[0][j] = j;
        }
        for (int i = 1; i <= length1; ++i) {
            table[i][0] = i;
            for (int j = 1; j <= length2; ++j) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    table[i][j] = table[i - 1][j - 1];
                }
                else {
                    table[i][j] = Math.min(Math.min(table[i][j - 1], table[i - 1][j]), table[i - 1][j - 1]) + 1;
                }
            }
        }
        return table[length1][length2];

    }


}
