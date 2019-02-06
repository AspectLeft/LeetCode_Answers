class WordDistance {
    private Map<String, List<Integer>> indexTable;

    public WordDistance(String[] words) {
        indexTable = new HashMap<>();
        for (int i = 0; i < words.length; ++i) {
            String word = words[i];
            if (!indexTable.containsKey(word)) {
                indexTable.put(word, new ArrayList<>());
            }
            indexTable.get(word).add(i);
        }
    }
    
    public int shortest(String word1, String word2) {
        List<Integer> indices1 = indexTable.get(word1), indices2 = indexTable.get(word2);
        int i1 = 0, i2 = 0, index1, index2;
        int output = Integer.MAX_VALUE;
        while (i1 < indices1.size() && i2 < indices2.size()) {
            index1 = indices1.get(i1);
            index2 = indices2.get(i2);
            if (index1 < index2) {
                output = Math.min(output, index2 - index1);
                i1++;
            }
            else {
                output = Math.min(output, index1 - index2);
                i2++;
            }
        }
        return output;
    }
}

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(words);
 * int param_1 = obj.shortest(word1,word2);
 */
