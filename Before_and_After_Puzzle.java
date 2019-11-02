class Solution {
    public List<String> beforeAndAfterPuzzles(String[] phrases) {
        Map<String, List<Integer>> prefixMap = new HashMap<>();
        Map<String, List<Integer>> suffixMap = new HashMap<>();
        List<String[]> splitted = new ArrayList<>();
        for (int i = 0; i < phrases.length; ++i) {
            String phrase = phrases[i];
            String[] words = phrase.split(" ");
            splitted.add(words);
            String prefix = words[0];
            String suffix = words[words.length - 1];
            
            List<Integer> prefixList = prefixMap.get(prefix);
            if (prefixList == null) {
                prefixList = new ArrayList<>();
                prefixMap.put(prefix, prefixList);
            }
            prefixList.add(i);
            
            List<Integer> suffixList = suffixMap.get(suffix);
            if (suffixList == null) {
                suffixList = new ArrayList<>();
                suffixMap.put(suffix, suffixList);
            }
            suffixList.add(i);
        }
        
        Set<String> results = new HashSet<>();
        
        for (int i = 0; i < phrases.length; ++i) {
            String[] words = splitted.get(i);
            String prefix = words[0];
            
            List<Integer> suffixList = suffixMap.get(prefix);
            if (suffixList == null) continue;
            for (int j: suffixList) {
                if (i == j) continue;
                results.add(phrases[j] + phrases[i].substring(prefix.length()));
            }
        }
        
        List<String> output = new ArrayList<>();
        output.addAll(results);
        Collections.sort(output);
        return output;
    }
}
