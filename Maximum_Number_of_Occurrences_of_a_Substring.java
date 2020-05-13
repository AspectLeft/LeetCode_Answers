class Solution {
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int[] table = new int[256];
        int count = 0;
        char c;
        for (int i = 0; i < minSize; ++i) {
            c = s.charAt(i);
            table[c]++;
            if (table[c] == 1) count++;
        }
        
        Map<String, Integer> map = new HashMap<>();
        if (count <= maxLetters) {
            map.put(s.substring(0, minSize), 1);
        }
        int start = 1;
        String sub;
        while (start + minSize <= s.length()) {
            c = s.charAt(start - 1);
            table[c]--;
            if (table[c] == 0) count--;
            
            c = s.charAt(start + minSize - 1);
            table[c]++;
            if (table[c] == 1) count++;
            
            if (count <= maxLetters) {
                sub = s.substring(start, start + minSize);
                map.put(sub, map.getOrDefault(sub, 0) + 1);
            }
            
            start++;
        }
        
        int max = 0;
        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            max = Math.max(max, entry.getValue());
        }
        return max;
    }
}
