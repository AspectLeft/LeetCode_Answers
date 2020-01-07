class Solution {
    public int[] findPermutation(String s) {
        int i = 0;
        int[] output = new int[s.length() + 1];
        int v = 1;
        while (i <= s.length()) {
            int j = i;
            while (j < s.length() && s.charAt(j) == 'D') j++;
            int k = j;
            while (k >= i) {
                output[k] = v;
                v++;
                k--;
            }
            i = j + 1;
        }
        return output;
    }
}
