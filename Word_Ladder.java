class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map <String, Integer> wordSet = new HashMap<>();
        int s = wordList.size();
        for (String word: wordList) 
            wordSet.put(word, 0);
        if (!wordSet.containsKey(endWord)) return 0;
        wordSet.put(endWord, 2);
        List<String> level = new ArrayList<>();
        level.add(beginWord);
        int count = 1;
        while (count <= s + 1) {
            count++;
            List <String> newLevel = new ArrayList<>();
            for (String word: level) {
                for (int i = 0; i < word.length(); ++i) {
                    char[] a = word.toCharArray();
                    char oldC = a[i];
                    for (char c = 'a'; c <= 'z'; ++c) {
                        if (c != oldC) {
                            a[i] = c;
                            String next = new String(a);
                            Integer x = wordSet.get(next);
                            if (x != null) {
                                if (x == 0) {
                                    newLevel.add(next);
                                    wordSet.remove(next);
                                }
                                if (x == 2) {
                                    return count;
                                }
                            }
                        }
                    }
                }
            }
            level = newLevel;
        }
        return 0;
    }
    
}
