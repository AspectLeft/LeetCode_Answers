class Solution {
    private Map<String, Integer> dict;
    
    private List<List<Integer>> dp;
    
    private List<String> output;
    
    private String s;
    
    public List<String> wordBreak(String s, List<String> wordDict) {
        if (s == null) return new ArrayList<>();
        this.s = s;
        dict = new HashMap<>();
        for (int i = 0; i < wordDict.size(); ++i) {
            dict.put(wordDict.get(i), i);
        }
        
        char[] a = s.toCharArray();
        dp = new ArrayList<>();
        List<Integer> init = new ArrayList<>();
        init.add(-1);
        dp.add(init);
        for (int i = 1; i < s.length() + 1; ++i) {
            List<Integer> total = new ArrayList<>();
            for (int j = 0; j < i; ++j) {
                List<Integer> sub = dp.get(j);
                if (!sub.isEmpty()) {
                    Integer tailIndex = dict.get(s.substring(j, i));
                    if (tailIndex != null) {
                        total.add(j);
                    }
                }
                
            }
            dp.add(total);
        }
        return build(s.length());
    }
    
    private List<String> build(int i) {
        List<String> result;
        if (i == 0) {
            result = new ArrayList<>();
            result.add("");
            return result;
        }
        result = new ArrayList<>();
        for (int j: dp.get(i)) {
            List<String> sub = build(j);
            for (String prefix: sub) {
                if (j != 0)
                    result.add(prefix + " " + s.substring(j, i));
                else
                    result.add(s.substring(0, i));
            }
        }
        return result;
    }
    
}
