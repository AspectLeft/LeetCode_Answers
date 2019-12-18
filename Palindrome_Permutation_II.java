class Solution {
    public List<String> generatePalindromes(String s) {
        int[] table = new int[256];
        
        for (int i = 0; i < s.length(); ++i) {
            table[s.charAt(i)]++;
        }
        
        String core = "";
        int oddCount = 0;
        
        for (int i = 0; i < 256; ++i) {
            if (table[i] % 2 == 1) {
                oddCount++;
                core = "" + (char) i;
            }
            if (oddCount > 1) return new ArrayList<>();
            table[i] /= 2;
        }
        Set<String> set = new HashSet<>();
        set.add("");
        for (int i = 0; i < 256; ++i) {
            char ch = (char) i;
            for (int j = 0; j < table[i]; ++j) {
                Set<String> nextSet = new HashSet<>();
                for (String base: set) {
                    String appending = base + ch;
                    nextSet.add(appending);
                    for (int k = base.length() - 1; k >= 0; --k) {
                        if (base.charAt(k) == ch) continue;
                        nextSet.add(base.substring(0, k) + ch 
                                    + base.substring(k));
                    }
                }
                set = nextSet;
            }
        }
        List<String> output = new ArrayList<>();
        for (String half: set) {
            output.add(half + core + 
                       new StringBuilder(half).reverse().toString());
        }
        return output;
    }
}
