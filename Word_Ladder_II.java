class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || endWord == null) return new ArrayList<>();
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= wordList.size(); ++i)
            adjList.add(new ArrayList<Integer>());
        
        int s = wordList.size(), t = -1;
        List<Integer> sList = adjList.get(s);
        
        for (int i = 0; i < s; ++i) {
            String a = wordList.get(i);
            if (a.equals(endWord))
                t = i;
            List<Integer> aList = adjList.get(i);
            for (int j = i + 1; j < s; ++j) {
                if (diff(a, wordList.get(j)) == 1) {
                    aList.add(j);
                    adjList.get(j).add(i);
                }
            }
            if (diff(a, beginWord) == 1) {
                aList.add(s);
                sList.add(i);
            }
        }
        
        List<List<String>> output = new ArrayList<>();
        if (t == -1) return output;
        List<List<Integer>> level = new ArrayList<>();
        level.add(new ArrayList<Integer>());
        level.get(0).add(s);
        
        int layer[] = new int[s + 1];
        Arrays.fill(layer, s + 1);
        layer[s] = 0;
        
        int count = 1;
        boolean flag = false;
        while (count <= s) {
            List<List<Integer>> newLevel = new ArrayList<>();
            for (List<Integer> path: level) {
                int tail = path.get(path.size() - 1);
                List<Integer> tailList = adjList.get(tail);
                for (int next: tailList) {
                    if (layer[next] >= count) {
                        layer[next] = count;
                        List<Integer> newPath = new ArrayList<>(path);
                        newPath.add(next);
                        newLevel.add(newPath);
                        if (next == t) flag = true;
                    }
                }
            }
            
            level = newLevel;
            
            if (flag) {
                for (List<Integer> path: level) {
                    int tail = path.get(path.size() - 1);
                    if (tail == t) {
                        List<String> ladder = new ArrayList<>();
                        ladder.add(beginWord);
                        for (int i = 1; i < path.size(); ++i)
                            ladder.add(wordList.get(path.get(i)));
                        output.add(ladder);
                    }
                }
                return output;
            }
            
            
            ++count;
        }
        
        
        return output;
    }
    
    
    
    private int diff(String a, String b) {
        int d = 0;
        for (int i = 0; i < a.length(); ++i)
            if (a.charAt(i) != b.charAt(i))
                d++;
        return d;
    }
}
