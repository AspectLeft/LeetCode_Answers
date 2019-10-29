class Solution {
    class CharCount {
        char c;
        int count;
        
        CharCount(char c) {
            this.c = c;
            this.count = 1;
        }
    }
    
    private List<CharCount> getCharCountList(String S) {
        List<CharCount> charCountList = new ArrayList<>();
        CharCount prev = new CharCount(S.charAt(0));
        charCountList.add(prev);
        char c;
        for (int i = 1; i < S.length(); ++i) {
            c = S.charAt(i);
            if (c == prev.c) {
                prev.count++;
            }
            else {
                prev = new CharCount(c);
                charCountList.add(prev);
            }
        }
        return charCountList;
    }
    
    private boolean strechy(List<CharCount> sList, List<CharCount> wList) {
        if (sList.size() != wList.size()) return false;
        CharCount s, w;
        for (int i = 0; i < sList.size(); ++i) {
            s = sList.get(i);
            w = wList.get(i);
            if (s.c != w.c) return false;
            if (s.count < w.count) return false;
            if (s.count > w.count && s.count < 3) return false;
        }
        return true;
    }
    
    
    public int expressiveWords(String S, String[] words) {
        if (S.length() == 0) {
            int result = 0;
            for (String word: words) {
                if (word.length() == 0) {
                    result++;
                }
            }
            return result;
        }
        List<CharCount> sList = getCharCountList(S);
        int result = 0;
        for (String word: words) {
            if (word.length() == 0) continue;
            List<CharCount> wList = getCharCountList(word);
            if (strechy(sList, wList)) {
                result++;
            }
        }
        return result;
    }
}
