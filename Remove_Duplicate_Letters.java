class Solution {
    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() == 0) return s;
        char[] arr = s.toCharArray();
        int[] count = new int[26];
        for (char c: arr)
            count[c - 'a']++;
        boolean[] settled = new boolean[26];
        int i = 0, candidateIndex = 0;
        char candidate = arr[0];
        StringBuilder builder = new StringBuilder();
        while (i < arr.length) {
            if (settled[arr[i] - 'a']) {
                i++;
                continue;
            }
            if (count[arr[i] - 'a'] == 1) {
                if (arr[i] < candidate) {
                    candidate = arr[i];
                    candidateIndex = i;
                }
                builder.append(candidate);
                for (int j = candidateIndex; j < i; ++j) {
                    if (!settled[arr[j] - 'a'])
                        count[arr[j] - 'a']++;
                }
                settled[candidate - 'a'] = true;
                i = candidateIndex;
                
                candidate = 'z' + 1;
            }
            else {
                if (arr[i] < candidate) {
                    candidate = arr[i];
                    candidateIndex = i;
                }
                count[arr[i] - 'a']--;
                i++;
            }
            
        }
        
        return builder.toString();
    }
}
