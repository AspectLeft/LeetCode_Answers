class Solution {
    class Letter implements Comparable<Letter> {
        char c;
        Set<Letter> succs;
        Set<Letter> precs;
        
        Letter(char c) {
            this.c = c;
            succs = new HashSet<>();
            precs = new HashSet<>();
        }

        public void addSucc(Letter letter) {
            this.succs.add(letter);
        }
        
        public void addPrec(Letter letter) {
            this.precs.add(letter);
        }
        
        public void removePrec(Letter letter) {
            this.precs.remove(letter);
        }
        
        @Override
        public int compareTo(Letter o) {
            return this.precs.size() - o.precs.size();
        }
    }
    
    private Map<Character, Letter> map = new HashMap<>();
    
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) return "";
        Set<Character> alphabet = new HashSet<>();
        for (String word: words) {
            for (int i = 0; i < word.length(); ++i) {
                alphabet.add(word.charAt(i));
            }
        }
        for (char c: alphabet) {
            map.put(c, new Letter(c));
        }
        for (int i = 1; i < words.length; ++i) {
            extractEdges(words[i - 1], words[i]);
        }
        List<Letter> tops = new ArrayList<>();
        for (Letter l: map.values()) {
            if (l.precs.size() == 0) {
                tops.add(l);
            }
        }
        StringBuilder builder = new StringBuilder();
        while (!tops.isEmpty()) {
            List<Letter> nextTops = new ArrayList<>();
            for (Letter top: tops) {
                builder.append(top.c);
                for (Letter succ: top.succs) {
                    succ.precs.remove(top);
                    if (succ.precs.size() == 0) {
                        nextTops.add(succ);
                    }
                }
            }
            tops = nextTops;
        }
        if (builder.length() < alphabet.size()) return "";
        return builder.toString();
    }
    
    private void extractEdges(String s, String t) {
        char c1, c2;
        Letter l1, l2;
        for (int i = 0; i < s.length() && i < t.length(); ++i) {
            c1 = s.charAt(i);
            c2 = t.charAt(i);
            if (c1 == c2) continue;
            l1 = map.get(c1);
            l2 = map.get(c2);
            l1.addSucc(l2);
            l2.addPrec(l1);
            return;
        }
    }
}
