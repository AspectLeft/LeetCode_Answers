public class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        int wordLength = words[0].length();
        int wordsNum = words.length;
        List<Integer> indices = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            Integer integer = map.get(word);
            if (integer == null) {
                map.put(word, 1);
            }
            else {
                map.put(word, integer + 1);
            }
        }

        int sLength = s.length();
        for (int i = 0; i < sLength; ++i) {
            boolean flag = true;
            String t = s.substring(i, sLength);
            if (t.length() < wordLength * wordsNum)
                break;
            Map<String, Integer> tmp = new HashMap<>(map);
            for (int j = 0; j < wordsNum; ++j) {
                String nextWord = t.substring(j * wordLength, j * wordLength + wordLength);
                Integer integer = tmp.get(nextWord);
                if (integer == null || integer == 0) {
                    flag = false;
                    break;
                }
                else {
                    tmp.put(nextWord, integer - 1);
                }
            }
            if (flag) {
                indices.add(i);
            }
            
        }


        return indices;
    }
}
