class Solution {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingInt(pair -> pair[1]));
        int i = 0;
        int count = 0;
        int tail = Integer.MIN_VALUE;
        while (i < pairs.length) {
            tail = pairs[i][1];
            count++;
            i++;
            while (i < pairs.length && pairs[i][0] <= tail) i++;
        }
        
        return count;
    }
}
