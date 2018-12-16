class Solution {
    
    
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] table = new int[n + 1];
        for (int citation: citations) {
            if (citation >= n) table[n]++;
            else table[citation]++;
        }
        if (table[n] == n) return n;
        for (int h = n - 1; h >= 0; h--) {
            table[h] += table[h + 1];
            if (table[h] >= h) return h;
        }
        return 0;
        
        /*
        Arrays.sort(citations);
        int n = citations.length;
        int h;
        for (int i = 0; i < n; ++i) {
            h = n - i;
            if (citations[i] >= h) return h;
        }
        return 0;
        */
    }
}
