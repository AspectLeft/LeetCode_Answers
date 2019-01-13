class Solution {
    public int characterReplacement(String s, int k) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        char[] a = s.toCharArray();
        if (k >= n - 1) return n;
        
        int output = k + 1;
        
        List<List<Integer>> indicesList = new ArrayList<>();
        for (int i = 0; i < 26; ++i)
            indicesList.add(new ArrayList<>());
        for (int i = 0; i < a.length; ++i)
            indicesList.get(a[i] - 'A').add(i);
        
        for (int i = 0; i < 26; ++i) {
            List<Integer> indices = indicesList.get(i);
            if (indices.isEmpty()) continue;
            
            int remaining = k;
            int start = 0, end = 1;
            while (end < indices.size()) {
                int i1 = indices.get(end - 1), i2 = indices.get(end);
                if (remaining >= i2 - i1 - 1) {
                    end++;
                    remaining -= i2 - i1 - 1;
                }
                else {
                    output = Math.max(output, remaining + i1 - indices.get(start) + 1);
                    start++;
                    remaining += indices.get(start) - indices.get(start - 1) - 1;
                }
            }
            output = Math.max(output, remaining + indices.get(end - 1) - indices.get(start) + 1);
            if (output > n) return n;
        }
        
        return output;
    }
}
