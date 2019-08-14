class Solution {
    Set<String> dict;
    
    
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        if (words == null || words.length == 0) return new ArrayList<>();
        Arrays.sort(words, Comparator.comparingInt(s -> s.length()));
        dict = new HashSet<>();
        List<String> results = new ArrayList<>();
        for (String word: words) {
            if (valid(word)) results.add(word);
            dict.add(word);
        }
        
        return results;
    }
    
    private boolean valid(String word) {
        int n = word.length();
        if (n < 2) return false;
        int[] seg = new int[n + 1];
        for (int k = 1; k <= n; ++k) {
            if (dict.contains(word.substring(0, k))) {
                seg[k] = 1;
            }
            if (k > dict.size()) {
                for (String suffix: dict) {
                    if (k > suffix.length() && seg[k - suffix.length()] > 0 
                        && word.substring(0, k).endsWith(suffix)) {
                        seg[k] = 2;
                        break;
                    }
                }
                continue;
            }
            
            
            for (int i = 1; i < k; ++i) {
                if (seg[i] > 0 && dict.contains(word.substring(i, k))) {
                    seg[k] = 2;
                    break;
                }
            }
        }
        return seg[n] == 2;
    }
}
