class Solution {
    private char get(List<String> words, int i, int j) {
        if (i >= words.size() || j >= words.get(i).length()) return '#';
        return words.get(i).charAt(j);
    }
    
    
    public boolean validWordSquare(List<String> words) {
        int maxLength = 0;
        for (String word: words) {
            maxLength = Math.max(maxLength, word.length());
        }
        if (maxLength != words.size()) return false;
        for (int i = 0; i < words.size(); ++i) {
            for (int j = 0; j < words.size(); ++j) {
                if (get(words, i, j) != get(words, j, i)) return false;
            }
        }
        return true;
    }
}
