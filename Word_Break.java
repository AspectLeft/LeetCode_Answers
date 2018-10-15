class Solution {
    private Set<String> dict;
    
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null) return true;
        dict = new HashSet<>();
        for (String word: wordDict)
            dict.add(word);
        
        char[] a = s.toCharArray();
        boolean[] dp = new boolean[a.length + 1];
        dp[0] = true;
        for (int i = 1; i < dp.length; ++i) {
            for (int j = 0; j < i; ++j) {
                if (dp[j] && dict.contains(new String(a, j, i - j))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[a.length];
    }
    
}
