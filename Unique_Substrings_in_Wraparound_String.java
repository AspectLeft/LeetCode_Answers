class Solution {
    public int findSubstringInWraproundString(String p) {
        if (p == null || p.length() == 0) return 0;
        if (p.length() == 1) return 1;
        int[] table = new int[26];
        int sublength = 1;
        table[p.charAt(0) - 'a'] = 1;
        for (int i = 1; i < p.length(); ++i) {
            if (p.charAt(i) == p.charAt(i - 1) + 1 
                || p.charAt(i) == p.charAt(i - 1) - 25) {
                sublength++;
            }
            else {
                sublength = 1;
            }
            if (sublength > table[p.charAt(i) - 'a'])
                table[p.charAt(i) - 'a'] = sublength;
        }
        int sum = 0;
        for (int v: table) sum += v;
        return sum;
    }
}
