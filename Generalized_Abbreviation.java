class Solution {
    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
    
    public List<String> generateAbbreviations(String word) {
        List<String> level = new ArrayList<>();
        if (word == null) return level;
        if (word.length() == 0) {
            level.add("");
            return level;
        }
        table = new boolean[word.length()];
        output = new ArrayList<>();
        dfs(0, word);
        return output;
    }
    
    private boolean[] table;
    private List<String> output;
    
    private void dfs(int i, String word) {
        if (i == word.length()) {
            StringBuilder builder = new StringBuilder();
            int j = 0, k = 0;
            while (j < table.length) {
                while (j < table.length && !table[j]) {
                    builder.append(word.charAt(j));
                    ++j;
                }
                if (j == table.length) break;
                k = j;
                while (k < table.length && table[k]) ++k;
                builder.append(k - j);
                j = k;
            }
            output.add(builder.toString());
            return;
        }
        dfs(i + 1, word);
        
        table[i] = true;
        dfs(i + 1, word);
        table[i] = false;
    }
}
