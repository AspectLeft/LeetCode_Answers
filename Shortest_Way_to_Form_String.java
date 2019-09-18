class Solution {
    public int shortestWay(String source, String target) {
        int start = 0;
        int i, j;
        char s, t;
        int count = 0;
        int[][] indicesAfter = new int[source.length()][26];
        for (int[] indices: indicesAfter) Arrays.fill(indices, -1);
        int m = source.length();
        indicesAfter[m - 1][source.charAt(m - 1) - 'a'] = m - 1;
        for (i = m - 2; i >= 0; --i) {
            indicesAfter[i] = Arrays.copyOf(indicesAfter[i + 1], 26);
            indicesAfter[i][source.charAt(i) - 'a'] = i;
        }
        
        
        while (start < target.length()) {
            i = 0;
            j = start;
            while (i < source.length() && j < target.length()) {
                t = target.charAt(j);
                
                i = indicesAfter[i][t - 'a'];
                if (i == -1) break;
                i++;
                j++;
            }
            if (j == start) return -1;
            start = j;
            count++;
        }
        return count;
    }
}
