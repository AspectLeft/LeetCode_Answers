class Solution {
    private int bitset(String word) {
        int set = 0;
        for (char c: word.toCharArray()) {
            set |= (1 << (c - 'a'));
            if (set == 0x3ffffff)
                return set;
        }
        return set;
    }
    
    public int maxProduct(String[] words) {
        if (words == null || words.length == 0) return 0;
        int[] sets = new int[words.length];
        for (int i = 0; i < words.length; ++i)
            sets[i] = bitset(words[i]);
        int result = 0;
        for (int i = 0; i < words.length; ++i) {
            for (int j = 0; j < words.length; ++j) {
                if ((sets[i] & sets[j]) == 0)
                    result = Math.max(result, words[i].length() * words[j].length());
            }
        }
        return result;
    }
}
