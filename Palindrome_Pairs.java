class Solution {
    private void updateMap(Map<String, List<Integer>> counterpart, String word, int i) {
        List<Integer> indices = counterpart.get(word);
        if (indices == null) {
            indices = new ArrayList<>();
            counterpart.put(word, indices);
        }
        indices.add(i);
    }
    
    
    public List<List<Integer>> palindromePairs(String[] words) {
        Map<String, List<Integer>> counterpart = new HashMap<>();
        Map<String, List<Integer>> original = new HashMap<>();
        for (int i = 0; i < words.length; ++i) {
            updateMap(original, words[i], i);
            for (int sum = 0; sum < words[i].length(); ++sum) {
                int l, r;
                for (l = 0, r = sum - l; l < r; ++l, --r) {
                    if (words[i].charAt(l) != words[i].charAt(r))
                        break;
                }
                if (l >= r) {
                    updateMap(counterpart, words[i].substring(sum + 1), i);
                }
            }
        }
        List<Set<Integer>> pairSets = new ArrayList<>();
        for (int i = 0; i < words.length; ++i) {
            Set<Integer> jset = new HashSet<>();
            List<Integer> j = counterpart.get(new StringBuilder(words[i]).reverse().toString());
            if (j != null) jset.addAll(j);
            
            j = original.get(new StringBuilder(words[i]).reverse().toString());
            if (j != null) jset.addAll(j);
            
            for (int sum = 2 * words[i].length() - 2; sum >= words[i].length() - 1; --sum) {
                int l, r;
                for (r = words[i].length() - 1, l = sum - r; l < r; ++l, --r) {
                    if (words[i].charAt(l) != words[i].charAt(r))
                        break;
                }
                if (l >= r) {
                    j = original.get(new StringBuilder(words[i].substring(0, sum - words[i].length() + 1)).reverse().toString());
                    if (j != null) jset.addAll(j);
                }
            }
            
            
            pairSets.add(jset);
        }
        List<List<Integer>> output = new ArrayList<>();
        for (int i = 0; i < words.length; ++i) {
            for (int j: pairSets.get(i)) {
                if (i == j) continue;
                List<Integer> pair = new ArrayList<>();
                pair.add(i);
                pair.add(j);
                output.add(pair);
            }
        }
        return output;
    }
}
