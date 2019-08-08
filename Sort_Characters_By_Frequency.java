class Solution {
    //Bucket sort
    public String frequencySort(String s) {
        int[] table = new int[256];
        for (char c: s.toCharArray()) {
            table[c]++;
        }
        Set<Character>[] buckets = new HashSet[s.length() + 1];
        
        for (int i = 0; i < 256; ++i) {
            if (table[i] == 0) continue;
            int bucketIndex = table[i];
            if (buckets[bucketIndex] == null) 
                buckets[bucketIndex] = new HashSet<Character>();
            buckets[bucketIndex].add((char) i);
        }
        
        
        StringBuilder builder = new StringBuilder();
        for (int bucketIndex = s.length(); bucketIndex > 0; --bucketIndex) {
            if (buckets[bucketIndex] == null) continue;
            for (char c: buckets[bucketIndex]) {
                for (int j = 0; j < bucketIndex; ++j) {
                    builder.append(c);
                }
            }
        }
        return builder.toString();
    }
}
